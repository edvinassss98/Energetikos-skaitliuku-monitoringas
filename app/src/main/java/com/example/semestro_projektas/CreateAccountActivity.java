package com.example.semestro_projektas;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateAccountActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ConnectionClass connectionClass;
    private EditText Name;
    private EditText Password;
    private String Type="";
    private Button Create;
    private Spinner spinner1;
    private static final String[] paths = {"Pasirinkti","Cecho vadovas", "Administratorius", "Inžinierius"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        connectionClass = new ConnectionClass();
        Name = (EditText)findViewById(R.id.edusername);
        Password = (EditText)findViewById(R.id.atpass);

        Create = (Button) findViewById(R.id.btnCreate) ;
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateAccountActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoCreate doCreate = new DoCreate();
                doCreate.execute("");
            }
        });



    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                Type="";
                break;
            case 1:
                Type = "1";
                break;
            case 2:
                Type = "2";
                break;
            case 3:
                Type = "3";
                break;

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
    public class DoCreate extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = Name.getText().toString();
        String password = Password.getText().toString();
        String type = String.valueOf(Type);
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {

            Toast.makeText(CreateAccountActivity.this,r,Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Toast.makeText(CreateAccountActivity.this,r,Toast.LENGTH_SHORT).show();
            }

        }


        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals("")|| type.trim().equals(""))
                z = "Įveskite visus duomenis";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (null == con) {
                        z = "Error in connection with SQL server";
                    } else {
                        /*INSERT INTO dbo.logins(EmpId, username, passwords, tipas)
                        VALUES ('5', '19', '19','1')*/
                        String query2 ="SELECT MAX(EmpId) AS LastID FROM dbo.logins";
                        Statement stmt1 = con.createStatement();
                        ResultSet rs = stmt1.executeQuery(query2);
                        int supplierID=0;
                        while (rs.next()) {
                            supplierID = rs.getInt("LastID");
                        }
                        supplierID++;

                        String query = "insert into dbo.logins(EmpID, username, passwords, tipas) values " +
                                "('" + supplierID + "','" +userid + "','" +password + "','" +type + "')";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);
                        z="Vartotojas " + userid + " sukurtas";
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.toString();
                }
            }
            return z;
        }
    }
    @Override
    public void onBackPressed() {
            finish();
        }

/*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

}
