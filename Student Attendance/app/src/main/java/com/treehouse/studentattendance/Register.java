package com.treehouse.studentattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText emailET;
    EditText passwordOneET;
    EditText passwordTwoET;
    Button registerBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = (EditText)findViewById(R.id.register_emailET);
        passwordOneET = (EditText)findViewById(R.id.register_passwordET_one);
        passwordTwoET = (EditText)findViewById(R.id.register_passwordET_two);
        registerBtn= (Button) findViewById(R.id.register_registerBtn);
        loginBtn = (Button)findViewById(R.id.register_loginBtn);

        //register button listener
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //login button listener
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Register.this, loginActivity.class);
                startActivity(intent);

            }
        });
    }
}