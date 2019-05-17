package com.example.semestro_projektas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WaterFragment extends Fragment {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_water, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Skaitliukai/Vandens");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String myValues = snapshot.getKey();
                    LinearLayout layout = (LinearLayout) getView().findViewById(R.id.layout);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    Button btn = new Button(getActivity());
                    btn.setText(myValues);
                    layout.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialogs(myValues);
                        }
                    });
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }
    public void Dialogs(final String id){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Skaitliukai/Vandens/"+id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String tipas = dataSnapshot.child("Informacija").child("Tipas").getValue(String.class);
                String Busena = dataSnapshot.child("Informacija").child("Busena").getValue(String.class);
                final String sanaudosG = dataSnapshot.child("Sanaudos").child("Geguze").getValue(String.class);
                final String sanaudosB = dataSnapshot.child("Sanaudos").child("Balandis").getValue(String.class);
                final String sanaudosK = dataSnapshot.child("Sanaudos").child("Kovas").getValue(String.class);
                final String sanaudosS = dataSnapshot.child("Sanaudos").child("Sausis").getValue(String.class);
                final String sanaudosV = dataSnapshot.child("Sanaudos").child("Vasaris").getValue(String.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.create();
                builder.setTitle("Informacija apie vandens skaitliuką");
                builder.setMessage("ID:" + id + "\n" + "Tipas: " + tipas + "\n" + "Būsena: " + Busena +
                        "\nSąnaudos einamąjį mėnėsį : " + sanaudosG )
                        .setPositiveButton("Daugiau informacijos", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(),ReportsFragment.class);
                                intent.putExtra("id", id);
                                intent.putExtra("geguze", sanaudosG);
                                intent.putExtra("balandis", sanaudosB);
                                intent.putExtra("kovas", sanaudosK);
                                intent.putExtra("sausis", sanaudosS);
                                intent.putExtra("vasaris", sanaudosV);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Uždaryti", new DialogInterface.OnClickListener() {
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
}
