package com.treehouse.studentattendance;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.treehouse.studentattendance.databinding.ActivityTeacherBinding;

public class TeacherActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationViewTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        //rocustomActionBar();

        replaceFragment(new TeacherReportFragment());

        bottomNavigationViewTeacher = (BottomNavigationView)findViewById(R.id.bottomNavTeacher);
        bottomNavigationViewTeacher.setOnItemSelectedListener( item -> {
            //when user select any button, it is stored in the item variable

            switch (item.getItemId()){

                case R.id.teacher_report:
                    replaceFragment(new TeacherReportFragment());
                    break;
                case R.id.teacher_timer:
                    replaceFragment((new TeacherCourseFragment()));
                    break;
                case R.id.teacher_profile:
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

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutTeacher, fragment);
        fragmentTransaction.commit();
    }


}