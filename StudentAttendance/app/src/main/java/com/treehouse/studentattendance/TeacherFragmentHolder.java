package com.treehouse.studentattendance;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherFragmentHolder extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_fragment_holder);

        customActionBar();

        //when the app starts, the teacher Report page fragment shows first
        replaceFragment(new TeacherReportFragment());


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener( item -> {
            //when user select any button, it is stored in the item variable

            switch (item.getItemId()){

                case R.id.report:
                    replaceFragment(new TeacherReportFragment());
                    break;
                case R.id.course:
                    replaceFragment((new TeacherCourseFragment()));
                    break;
                case R.id.profile:
                    replaceFragment(new TeacherProfileFragment());
                    break;
            }


            return true;
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

    //method to replace the frame layout in the teacher's activity layout by the fragment layouts for the teacher
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.teacher_frameLayout, fragment);
        fragmentTransaction.commit();
    }

}