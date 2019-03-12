package com.example.semestro_projektas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPass);
        Login = (Button)findViewById(R.id.btnLogin) ;
        Info = (TextView)findViewById(R.id.twCheck) ;

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });
    }


    private void validate(String userName, String userPassword){
        if((userName.equals("Admin"))&& (userPassword.equals("1234"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Info.setText("Neteisingi prisijungimo duomenys");

        }

    }
}
