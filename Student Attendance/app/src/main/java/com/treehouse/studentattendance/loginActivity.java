package com.treehouse.studentattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    EditText emailET;
    EditText passwordET;
    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = (EditText)findViewById(R.id.login_emailET);
        passwordET = (EditText)findViewById(R.id.login_passwordET);
        loginBtn = (Button) findViewById(R.id.login_loginBtn);
        registerBtn = (Button)findViewById(R.id.login_registerBtn);

        //login button listener
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //register button listener
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}