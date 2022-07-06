package com.treehouse.studentattendance;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import java.util.concurrent.Executor;
public class BiometricActivity extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometric);
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




//                Intent i = new Intent(BiometricActivity.this, attendFragment.getClass());
//                startActivity(i);
            }

            //authentication fail
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Student App")
                .setSubtitle("Use FingerPrint to continue").setNegativeButtonText("Cancel").build();
        biometricPrompt.authenticate(promptInfo);
    }
}