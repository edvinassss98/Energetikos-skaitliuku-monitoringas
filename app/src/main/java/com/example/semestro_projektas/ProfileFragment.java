package com.example.semestro_projektas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ListView view;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Button b = rootView.findViewById(R.id.chngbtn);

        b.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ChangingPassword.class);
                startActivity(intent);

            }

        });


        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        super.onCreate(savedInstanceState);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void showdata(DataSnapshot dataSnapshot) {

        TextView type = (TextView)getView().findViewById(R.id.pareig);
        TextView worksSince = (TextView)getView().findViewById(R.id.dirbn);
        TextView id = (TextView)getView().findViewById(R.id.textid);
        TextView Warnings = (TextView)getView().findViewById(R.id.warnings);
        TextView email = (TextView)getView().findViewById(R.id.emailid);
        TextView name = (TextView)getView().findViewById(R.id.darte2);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println(currentFirebaseUser.getEmail());
        final String a = currentFirebaseUser.getUid();
        String tipas = dataSnapshot.child(a).child("Tipas").getValue(String.class);
        String vardas = dataSnapshot.child(a).child("Vardas").getValue(String.class);
        String dirban = dataSnapshot.child(a).child("Dirbanuo").getValue(String.class);
        String warnin = dataSnapshot.child(a).child("Pazeidimai").getValue(String.class);
        if (tipas.contains("1")) {
            tipas = "Cecho vadovas";
        } else if (tipas.contains("2")) {
            tipas = "Administratorius";
        } else if (tipas.contains("3")) {
            tipas = "Inžinierius";
        }
        type.setText("Pareigos - "+tipas);
        worksSince.setText("Dirba nuo - - "+dirban);
        id.setText("Vartotojo ID  - "+a);
        email.setText("Email - "+currentFirebaseUser.getEmail());
        name.setText("Vardas  - "+vardas);
        Warnings.setText("Įspėjimai - "+warnin);
    }



}




