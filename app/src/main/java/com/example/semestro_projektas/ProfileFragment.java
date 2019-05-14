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

        GetData();
        return rootView;
    }
    public void GetData(){


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        System.out.println(currentFirebaseUser.getEmail());
        final String a = currentFirebaseUser.getUid();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String tipas = dataSnapshot.child(a).child("Tipas").getValue(String.class);

               if(tipas.contains("1")){
                   tipas = "Cecho vadovas";
               }else if(tipas.contains("2")){
                   tipas = "Administratorius";
               }else  if(tipas.contains("3")){
                   tipas = "Inžinierius";
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
