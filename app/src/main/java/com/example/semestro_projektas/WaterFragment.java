package com.example.semestro_projektas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class WaterFragment extends Fragment {

    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_water, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        new FirebaseDatabaseHelper().readCounters(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Counter> counters, List<String> keys) {
                new RecyclerViewConfig().setConfig(mRecyclerView, getActivity(),counters,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        return rootView;
    }
}
