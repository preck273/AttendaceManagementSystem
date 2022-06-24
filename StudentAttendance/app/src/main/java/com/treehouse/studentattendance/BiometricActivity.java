package com.treehouse.studentattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class BiometricActivity extends AppCompatActivity {

    //initialize variables
    private long duration;  //set end time in seconds
    private TextView hour, min, sec;
    private EditText timeInput;
    private Button start_btn, button_set, attend_btn;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    //private Button btn_attend, btn_auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometric);
        // Assign variables
        hour = findViewById(R.id.hour);
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);
        start_btn = findViewById(R.id.start_btn);
        attend_btn = findViewById(R.id.attend_btn);
        attend_btn.setVisibility(View.GONE);

        //initialize biometrics
        executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(BiometricActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            //if error occurs during authentication
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(BiometricActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

            //authentication successful
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(BiometricActivity.this, "Attendance Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BiometricActivity.this, ReportFragment.class);
                startActivity(intent);
            }

            //authentication fail
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Student App")
                .setSubtitle("Use FingerPrint to continue").setNegativeButtonText("Cancel").build();

        button_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CountDownTimer(duration * 1000, 1000) {
                    @Override
                    public void onTick(long l) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String timer = String.format(Locale.getDefault(),"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(l),
                                        TimeUnit.MILLISECONDS.toMinutes(l)-
                                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l)),
                                        TimeUnit.MILLISECONDS.toSeconds(l)-
                                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                                final String[] hourMinSec = timer.split(":");

                                hour.setText(hourMinSec[0]);
                                min.setText(hourMinSec[1]);
                                sec.setText(hourMinSec[2]);
                                start_btn.setVisibility(View.GONE);
                                attend_btn.setVisibility(View.VISIBLE);
                            }
                        });
                    }

                    @Override
                    public void onFinish() {
                        //restart timer duration
                        duration = 120;
                        start_btn.setVisibility(View.VISIBLE);
                        attend_btn.setVisibility(View.GONE);
                    }
                }.start();
                //}else{
                //Toast.makeText(CountDownTimActivity.this, "Timer is already running", Toast.LENGTH_SHORT).show();
                // }
            }
        });
        attend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}