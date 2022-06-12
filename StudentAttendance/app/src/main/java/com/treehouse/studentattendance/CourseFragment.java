package com.treehouse.studentattendance;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CourseFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    Button attendBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_course, container, false);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize view here
        recyclerView = view.findViewById(R.id.courseRecyclerView);
        attendBtn = view.findViewById(R.id.course_attend_btn);


        //attend button listener
        attendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //goes to other fragments
                switchFragment();

            }
        });
    }

    private void switchFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new AttendFragment());
        fragmentTransaction.commit();
    }
}