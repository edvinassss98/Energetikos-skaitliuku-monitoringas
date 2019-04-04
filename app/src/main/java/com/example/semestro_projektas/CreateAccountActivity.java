package com.example.semestro_projektas;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateAccountActivity extends Activity {

    ConnectionClass connectionClass;
    private EditText Name;
    private EditText Password;
    private EditText Type;
    private Button Create;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        connectionClass = new ConnectionClass();
        Name = (EditText)findViewById(R.id.edusername);
        Password = (EditText)findViewById(R.id.atpass);
        Type = (EditText)findViewById(R.id.ettipas) ;
        Create = (Button) findViewById(R.id.btnCreate) ;

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoCreate doCreate = new DoCreate();
                doCreate.execute("");
            }
        });



    }
    public class DoCreate extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = Name.getText().toString();
        String password = Password.getText().toString();
        String type = Type.getText().toString();
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
}
