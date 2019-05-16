package com.example.semestro_projektas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsersList extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String myValues = snapshot.child("Email").getValue(String.class);
                     String tipas = snapshot.child("Tipas").getValue(String.class);
                    LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    Button btn = new Button(UsersList.this);

                    layout.addView(btn);
                    if (tipas.contains("1")) {
                        tipas = "Cecho vadovas";
                        btn.setText(myValues+ "  "+ tipas);
                    } else if (tipas.contains("2")) {
                        tipas = "Administratorius";
                        btn.setText(myValues+ "  "+ tipas);
                    } else if (tipas.contains("3")) {
                        tipas = "Inžinierius";
                        btn.setText(myValues+ "  "+ tipas);
                    }

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
