package com.treehouse.studentattendance;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class AttendFragment extends Fragment {

    private View view;
    private long duration;  //set end time in seconds
    private TextView hour, min, sec;
    private EditText timeInput;
    private Button start_btn, button_set, attend_btn;

    // A class instance to loop the timer
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_attend, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initiate views here
        hour = view.findViewById(R.id.hour);
        min = view.findViewById(R.id.min);
        sec = view.findViewById(R.id.sec);
        start_btn = view.findViewById(R.id.start_btn);
        attend_btn = view.findViewById(R.id.attend_btn);

        attend_btn.setVisibility(View.GONE);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CountDownTimer(duration * 1000, 1000) {
                    @Override
                    public void onTick(long l) {

                        mHandler.post(new Runnable() {
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
                Intent intent = new Intent(getActivity(), BiometricActivity.class);
                startActivity(intent);
            }
        });

    }
}