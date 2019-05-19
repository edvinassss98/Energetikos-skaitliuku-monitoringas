package com.example.semestro_projektas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private List<String> data = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private List<WorkshopInformation> workshopInformations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Pagrindinis");
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        mProgressDialog = new ProgressDialog(getActivity());
        return rootView;
    }
    public void setText(String a){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("information");
        super.onCreate(savedInstanceState);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProgressDialog.setMessage("Kraunami duomenys");
                showdata(dataSnapshot);
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void showdata(DataSnapshot dataSnapshot) {
        String Adresas = dataSnapshot.child("Adresas").getValue(String.class);
        Long Aukstai = dataSnapshot.child("Aukstai").getValue(Long.class);
        String Cecho_vad = dataSnapshot.child("Cecho_vad").getValue(String.class);
        Long Darb_Sk = dataSnapshot.child("Darb_Sk").getValue(Long.class);
        String Pavadinimas = dataSnapshot.child("Pavadinimas").getValue(String.class);
        TextView pava=(TextView) getView().findViewById(R.id.pavadin);
        TextView Adresas1=(TextView) getView().findViewById(R.id.adresas);
        TextView Cecho_vad1=(TextView) getView().findViewById(R.id.vadov);
        TextView Darb_Sk1=(TextView) getView().findViewById(R.id.Darb_sk);
        TextView Aukstai1=(TextView) getView().findViewById(R.id.aukstai);
        pava.setText("Darbovietės pavadinimas - "+Pavadinimas);
        Adresas1.setText("Darbovietės adresas - "+Adresas);
        Cecho_vad1.setText("Priskirtas cecho vadovas - "+Cecho_vad);
        Darb_Sk1.setText("Užregistruotų darbuotojų skaičius - "+Darb_Sk);
        Aukstai1.setText("Pastato aukštų skaičius - "+Aukstai);
    }
}
