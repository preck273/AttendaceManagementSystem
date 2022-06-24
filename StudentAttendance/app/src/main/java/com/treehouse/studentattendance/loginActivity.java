package com.treehouse.studentattendance;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class loginActivity extends AppCompatActivity {

    private EditText email_address, student_password;
    private Button loginBtn;
    private final String URL_LOGIN = "http://10.0.2.2/attendanceApp/login.php";
    private TextView registerTV, teacherLogin;


    //SessionManager sessionManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //action bar
        customActionBar();

        //sessionManage = new SessionManager(this);

        email_address = findViewById(R.id.email_address);
        student_password = findViewById(R.id.student_password);
        loginBtn =findViewById(R.id.login_loginBtn);
        registerTV = findViewById(R.id.login_register_TV);
        teacherLogin = findViewById(R.id.login_teacherLogin_TV);

        //login button listener
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        //register button listener
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginActivity.this, Register.class);
                startActivity(intent);
            }
        });

        teacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginActivity.this, TeacherLogin.class);
                startActivity(intent);
            }
        });

    }

    private void Login() {
        final String email = this.email_address.getText().toString().trim();
        final String password = this.student_password.getText().toString().trim();

        if (!email.equals("") && !password.equals("")) {
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

                                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                                    finish();

                                    Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
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
                            Toast.makeText(loginActivity.this, "Login Fail!!" + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    })
            {
                // @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email_address", email);
                    data.put("student_password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else {
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
