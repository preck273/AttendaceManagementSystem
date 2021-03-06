package com.treehouse.studentattendance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TeacherLogin extends AppCompatActivity {

    private TextView teacherEmail, teacherPass, studentLogin;
    private Button loginBtn;

    private static String URL_LOGIN = " http://192.168.43.46/attendanceApp/teacherLogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        customActionBar();

        teacherEmail = findViewById(R.id.email_address);
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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

    }

        private void Login() {
            final String email_address = this.teacherEmail.getText().toString().trim();
            final String teacher_password = this.teacherPass.getText().toString().trim();

            if (!email_address.equals("") && !teacher_password.equals("")) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);

                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {

                                        //String fName = jsonObject.getString("name").trim();

                                        // sessionManage.createSession();

                                        startActivity(new Intent(TeacherLogin.this, TeacherActivity.class));
                                        finish();

                                        Toast.makeText(TeacherLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(TeacherLogin.this);
                                        builder.setMessage("Login Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(TeacherLogin.this, "Login Fail" + error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("email_address", email_address);
                        data.put("teacher_password", teacher_password);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            } else {
                Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
            }
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