package com.example.semestro_projektas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class LogOffActivity extends AppCompatDialogFragment {


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Informacija");
        builder.setMessage("Norite atsijungti?");
        builder.setPositiveButton("Atsijungti", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(getContext(), LoginActivity.class); //if under this dialog you do not have your MainActivity
                i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }


        });
        builder.setNegativeButton("Atšaukti", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

}
