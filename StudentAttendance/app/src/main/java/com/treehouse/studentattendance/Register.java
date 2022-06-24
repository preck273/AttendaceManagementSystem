package com.treehouse.studentattendance;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Register extends AppCompatActivity {
    private EditText first_name, last_name, email_address, student_password;
    private TextView tvStatus;
    private static String URL_REGISTER ="http://10.0.2.2/attendanceApp/register.php";
    private Button registerBtn;
    private TextView loginET;
    private TextView teacherLoginTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //action bar
        customActionBar();

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email_address = findViewById(R.id.email_address);
        student_password = findViewById(R.id.student_password);
        registerBtn= (Button) findViewById(R.id.register_registerBtn);
        loginET = (TextView)findViewById(R.id.register_login_TV);
        teacherLoginTV = findViewById(R.id.register_teacher_login_TV);

        //register button listener
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Register();
            }
        });

        //login button listener
        loginET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Register.this, loginActivity.class);
                startActivity(intent);

            }
        });

        //teacher login button listener
        teacherLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Register.this, TeacherLogin.class);
                startActivity(intent);

            }
        });

    }

    @SuppressLint("NotConstructor")
    public void Register() {

        final String first_name = this.first_name.getText().toString().trim();
        final String last_name = this.last_name.getText().toString().trim();
        final String email_address = this.email_address.getText().toString().trim();
        final String student_password = this.student_password.getText().toString().trim();

        if (!first_name.equals("") && !last_name.equals("") && !email_address.equals("") && !student_password.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                //boolean emailAlreadyExist = jsonResponse.getBoolean("exist");

//                                if (emailAlreadyExist){
//                                    Toast.makeText(RegisterActivity.this, "Email already Exist", Toast.LENGTH_SHORT).show();
//                                }else {

                                if (success) {
                                    Intent intent = new Intent(Register.this, loginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                    //}
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Register.this, "Registration Error!!" + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                // @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("first_name", first_name);
                    data.put("last_name", last_name);
                    data.put("email_address", email_address);
                    data.put("student_password", student_password);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Fields can't be empty!", Toast.LENGTH_LONG).show();
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