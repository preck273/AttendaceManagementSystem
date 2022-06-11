package com.treehouse.studentattendance;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ReportFragment extends Fragment {

    View view;
    Button temporaryLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize view here

        //temporary intent to login page
        temporaryLogin = view.findViewById(R.id.temp_login);
        temporaryLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when in fragment instead of using ReportFragment.this,  use getActivity() instead
                Intent intent = new Intent(getActivity(), loginActivity.class);
                startActivity(intent);
            }
        });
    }
}