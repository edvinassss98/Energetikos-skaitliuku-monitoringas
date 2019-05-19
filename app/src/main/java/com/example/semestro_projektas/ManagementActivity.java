package com.example.semestro_projektas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ManagementActivity extends Fragment {
    private Button Create;
    ConnectionClass connectionClass;
    View inflatedView = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_management, container, false);
        Button b = rootView.findViewById(R.id.btn1Create);
        Button c = rootView.findViewById(R.id.btn1Create2);
        b.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
                startActivity(intent);
            }

        });
        c.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), UsersList.class);
                startActivity(intent);
            }

        });
        return rootView;
    }












}