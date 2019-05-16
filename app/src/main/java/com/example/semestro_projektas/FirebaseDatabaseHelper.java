package com.example.semestro_projektas;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceCounters;
    private List<Counter> counters = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Counter>counters,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper() {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceCounters = mDataBase.getReference("Skaitliukai/Vandens");


    }


    public void readCounters(final DataStatus dataStatus){
        mReferenceCounters.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                counters.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Counter counter = keyNode.getValue(Counter.class);
                    counters.add(counter);
                }
                dataStatus.DataIsLoaded(counters,keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
