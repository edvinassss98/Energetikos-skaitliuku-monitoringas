package com.example.semestro_projektas;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangingPassword extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private String Type = "";
    private Button Create;
    private FirebaseAuth mAuth;
    private Spinner spinner;
    private ProgressDialog mProgress;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText oldpsw;
    private EditText newpsw;
    private EditText newpsw2;
    private Button change;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changing_password);
        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        change = (Button) findViewById(R.id.change2);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);

    }

    private void doLogin() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println(currentFirebaseUser.getEmail());
        final String a = currentFirebaseUser.getUid();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String email = dataSnapshot.child(a).child("Email").getValue(String.class);
                oldpsw = findViewById(R.id.oldpsw);
                newpsw = findViewById(R.id.newpsw1);
                newpsw2 = findViewById(R.id.newpsw2);
                final String oldp = oldpsw.getText().toString().trim();
                final String newp = newpsw.getText().toString().trim();
                final String newp2 = newpsw2.getText().toString().trim();
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
                final DatabaseReference currentUserDB = mDatabase.child(mAuth.getCurrentUser().getUid());

                if (!TextUtils.isEmpty(oldp) && !TextUtils.isEmpty(newp) && !TextUtils.isEmpty(newp2)&&newp.equals(newp2)) {
                    AuthCredential credential = EmailAuthProvider.getCredential(email, oldp);
                    mProgress.setMessage("Slaptažodis keičiamas");
                    mProgress.show();
                    currentFirebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mProgress.dismiss();
                                currentFirebaseUser.updatePassword(newp2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            currentUserDB.child("Slaptažodis").setValue(newp2);
                                            Toast.makeText(ChangingPassword.this, "Pakeitimas sėkmingas", Toast.LENGTH_SHORT).show();
                                        } else
                                            Toast.makeText(ChangingPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                mProgress.dismiss();
                                Toast.makeText(ChangingPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else
                    Toast.makeText(ChangingPassword.this, "Įveskite visus laukus arba slaptažodžiai nesutinka", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

}
