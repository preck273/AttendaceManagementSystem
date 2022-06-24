package com.treehouse.studentattendance;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherLogin extends AppCompatActivity {

    private TextView teacherEmail, teacherPass, studentLogin;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        customActionBar();

        teacherEmail = findViewById(R.id.teacher_email_address);
        teacherPass = findViewById(R.id.teacher_password);
        studentLogin = findViewById(R.id.teacher_login_student_TV);
        loginBtn = findViewById(R.id.teacher_login_loginBtn);

        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TeacherLogin.this, loginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void customActionBar() {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //custom image for action bar
        actionbar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_header, null);
        actionbar.setCustomView(view);
    }
}