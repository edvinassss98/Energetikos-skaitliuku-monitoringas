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
public class LoginActivity extends Activity {

    ConnectionClass connectionClass;
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private ProgressBar pbbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//jkb
        connectionClass = new ConnectionClass();
        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPass);
        Login = (Button)findViewById(R.id.btnLogin) ;
        Info = (TextView)findViewById(R.id.twCheck) ;
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin doLogin = new DoLogin();
                doLogin.execute("");
            }
        });
    }
    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = Name.getText().toString();
        String password = Password.getText().toString();
        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this,r,Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Toast.makeText(LoginActivity.this,r,Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (null == con) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "select * from dbo.logins where username='" + userid + "' and passwords='" + password + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        if(rs.next())
                        {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }

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

/*
    private void validate(String userName, String userPassword){
        if((userName.equals("Admin"))&& (userPassword.equals("1234"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Info.setText("Neteisingi prisijungimo duomenys");

        }
        }
*/

}
