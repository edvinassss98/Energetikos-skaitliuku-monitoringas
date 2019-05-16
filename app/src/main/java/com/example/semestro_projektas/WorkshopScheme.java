package com.example.semestro_projektas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;


public class WorkshopScheme extends Fragment {


    private ImageButton button;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private ImageButton button6;
    private ImageButton button7;
    private ImageButton button8;
    private ImageButton button9;
    private ImageButton button10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.workshop_scheme, container, false);

        super.onCreate(savedInstanceState);

        button2=(ImageButton)rootView.findViewById(R.id.imageButton2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Elektros/E1");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatas(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie elektros skaitliuką");
                        builder.setMessage("ID: E1\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);

                                    alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            String result = input.getText().toString();
                                            if(!result.isEmpty()) {
                                                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                                currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                                DatabaseReference currentUserDB = mDatabase.child("E1_" + currentDateTimeString);
                                                currentUserDB.child("Tekstas").setValue(result);
                                            }else{
                                                Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                            }
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

        button3=(ImageButton)rootView.findViewById(R.id.imageButton3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Dujos/D1");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatasDujos(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie dujų skaitliuką");
                        builder.setMessage("ID: D1\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("D1_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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

        button4=(ImageButton)rootView.findViewById(R.id.imageButton4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Dujos/D2");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatasDujos(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie dujų skaitliuką");
                        builder.setMessage("ID: D2\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("D2_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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

        button5=(ImageButton)rootView.findViewById(R.id.imageButton5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Elektros/E2");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatas(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie elektros skaitliuką");
                        builder.setMessage("ID: E2\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("E2_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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

        button6=(ImageButton)rootView.findViewById(R.id.imageButton6);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Elektros/E3");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatas(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie elektros skaitliuką");
                        builder.setMessage("ID: E3\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("E3_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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
        button7=(ImageButton)rootView.findViewById(R.id.imageButton7);

        button7.setOnClickListener(new View.OnClickListener() {
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
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie vandens skaitliuką");
                        builder.setMessage("ID: V1\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("V1_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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

        button8=(ImageButton)rootView.findViewById(R.id.imageButton8);

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Vandens/V2");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatas(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie elektros skaitliuką");
                        builder.setMessage("ID: V2\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("V2_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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
        button9=(ImageButton)rootView.findViewById(R.id.imageButton9);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Vandens/V3");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatas(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie elektros skaitliuką");
                        builder.setMessage("ID: V3\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("V3_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
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
        button10=(ImageButton)rootView.findViewById(R.id.imageButton10);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference("Skaitliukai/Dujos/D3");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tipas= showdatat(dataSnapshot);
                        busena= showdatab(dataSnapshot);
                        sanaudos= showdatasDujos(dataSnapshot);
                        AlertDialog.Builder builder = new Builder(getActivity());
                        builder.create();
                        builder.setTitle("Informacija apie elektros skaitliuką");
                        builder.setMessage("ID: D3\n"+"Tipas: " + tipas+"\n"+"Būsena: "+busena+
                                "\nSąnaudos: "+sanaudos).setPositiveButton("Registruoti pranešimą", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                LayoutInflater li = LayoutInflater.from(getActivity());
                                View promptsView = li.inflate(R.layout.workshop_scheme, null);

                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                                alert.setTitle("Registruojamas pranešimas");

                                final EditText input = new EditText(getActivity());
                                alert.setView(input);
                                alert.setPositiveButton("Siųsti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String result = input.getText().toString();
                                        if(!result.isEmpty()) {
                                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                            currentDateTimeString = currentDateTimeString.replaceAll("\\s+", " ");

                                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Pranešimas");
                                            DatabaseReference currentUserDB = mDatabase.child("D3_" + currentDateTimeString);
                                            currentUserDB.child("Tekstas").setValue(result);
                                        }else{
                                            Toast.makeText(getActivity(),"Tusčia žinutė",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                alert.setNegativeButton("Atšaukti", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                });
                                alert.show();

                            }
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
        return rootView;

    }
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    String busena;
    String tipas;
    String sanaudos;

    private FirebaseAuth.AuthStateListener mAuthListener;


    public String showdatab(DataSnapshot dataSnapshot) {
        return dataSnapshot.child("Informacija").child("Busena").getValue(String.class);
    }
    public String showdatat(DataSnapshot dataSnapshot) {
        return dataSnapshot.child("Informacija").child("Tipas").getValue(String.class);
    }
    public String showdatasDujos(DataSnapshot dataSnapshot) {
        return dataSnapshot.child("Sanaudos").child("Geguze/Kiekis").getValue(String.class);

    }
    public String showdatas(DataSnapshot dataSnapshot){
        return dataSnapshot.child("Sanaudos").child("Geguze").getValue(String.class);
    }
}
