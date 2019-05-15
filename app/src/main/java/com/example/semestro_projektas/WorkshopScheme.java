package com.example.semestro_projektas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class WorkshopScheme extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    String busena;
    String tipas;
    String sanaudos;
    final Context context = this;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshop_scheme);

        final ImageButton button=(ImageButton)findViewById(R.id.imageButton1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Vandens/V1");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       tipas= showdatat(dataSnapshot);
                       busena= showdatab(dataSnapshot);
                       sanaudos= showdatas(dataSnapshot);
                       AlertDialog.Builder builder = new Builder(WorkshopScheme.this);
                       builder.create();
                       builder.setTitle("Informacija apie skaitliuką");
                       builder.setMessage("ID: V1\n"+"Tipas: " + tipas+"\n"+"Busena: "+busena+
                               "\nSąnaudos:"+sanaudos+"\nPranešimas").setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(context);
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(WorkshopScheme.this);
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(WorkshopScheme.this);
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                        DatabaseReference currentUserDB = mDatabase.child("V1");
                                        currentUserDB.child("Tekstas").setValue(result);
                                    }
                                });

                                alert.setNegativeButton("Atšaukti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                });
                                alert.show();

                            };
                        });
                       builder.setNegativeButton("Atšaukti", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       });
                       builder.show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
    public String showdatab(DataSnapshot dataSnapshot) {
    String busena1 = dataSnapshot.child("Informacija").child("Busena").getValue(String.class);

    return busena1;

    }
    public String showdatat(DataSnapshot dataSnapshot) {
        String tipas1 = dataSnapshot.child("Informacija").child("Tipas").getValue(String.class);

        return tipas1;

    }
    public String showdatas(DataSnapshot dataSnapshot) {

        String sanaudos1 = dataSnapshot.child("Sanaudos").child("Geguze").getValue(String.class);

        return sanaudos1;

    }
}
