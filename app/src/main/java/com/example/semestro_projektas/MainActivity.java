package com.example.semestro_projektas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private long backPressedTime;
    private Toast backToast;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String tipas = showdata(dataSnapshot);
                if(tipas.contains("2")){
                navigationView.getMenu().getItem(4).setVisible(false);
                navigationView.getMenu().getItem(6).setVisible(false);
                }else if(tipas.contains("3")){
                    navigationView.getMenu().getItem(4).setVisible(false);
                    navigationView.getMenu().getItem(6).setVisible(false);
                    navigationView.getMenu().getItem(1).setVisible(false);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.userEmail);
        navUsername.setText(currentFirebaseUser.getEmail());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InformationActivity()).commit();
            navigationView.setCheckedItem(R.id.nav_Infomacija);
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public String showdata(DataSnapshot dataSnapshot) {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String a = currentFirebaseUser.getUid();
        String busena1 = dataSnapshot.child(a).child("Tipas").getValue(String.class);

        return busena1;

    }




    //Here is if menu fragment is pushed, moves to that fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int selectedposition= 0;
        switch (menuItem.getItemId())
        {

            case R.id.Electricity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ElectricityFragment()).commit();

                break;
            case R.id.Gas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GasFragment()).commit();

                break;
            case R.id.Water:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WaterFragment()).commit();

                break;
            case R.id.nav_Ispejimai:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WarningsFragment()).commit();
                break;
            case R.id.nav_Atsijungti:
                turnOff();
                break;
            case R.id.nav_Valdymas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ManagementActivity()).commit();
                break;
            case R.id.nav_Profilis:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.nav_Infomacija:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InformationActivity()).commit();
                break;
            case R.id.nav_Schema:
                int item = menuItem.getItemId();
                System.out.println( item);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WorkshopScheme()).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InformationActivity()).commit();
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 1000 > System.currentTimeMillis() ) {
            backToast.cancel();
            System.exit(0);
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Paspauskite dar kartą jog išeiti", Toast.LENGTH_SHORT);
            backToast.show();

        }
        backPressedTime = System.currentTimeMillis();
/*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }


    public void turnOff(){
        LogOffActivity logOffDialog = new LogOffActivity();
        logOffDialog.show(getSupportFragmentManager(),"logofdialog");
    }


}
