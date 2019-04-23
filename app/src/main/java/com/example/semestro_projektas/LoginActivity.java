package com.example.semestro_projektas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class LoginActivity extends Activity {

    private long backPressedTime;
    private Toast backToast;
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private ProgressBar pbbar;
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Name = (EditText)findViewById(R.id.etName);
        progressDialog = new ProgressDialog(this);
        Password = (EditText)findViewById(R.id.etPass);
        Login = (Button)findViewById(R.id.btnLogin) ;
        mAuth = FirebaseAuth.getInstance();
        Info = (TextView)findViewById(R.id.twCheck) ;
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Toast.makeText(LoginActivity.this, "Prisijungė vartotojas - " + firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    //mAuth.signOut();
                }
            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void doLogin() {
        String email = Name.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
        progressDialog.setMessage("Prisijungiama, prašome palaukti");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Prisijungimas sėkmingas", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }else
            Toast.makeText(LoginActivity.this, "Įveskite visus laukus", Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 1000 > System.currentTimeMillis() ) {
            backToast.cancel();
            System.exit(0);
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Paspauskite dar kartą jog išeiti", Toast.LENGTH_SHORT);
            backToast.show();

        }
        backPressedTime = System.currentTimeMillis();
/*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

}
