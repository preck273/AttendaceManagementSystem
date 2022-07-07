package com.treehouse.studentattendance;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ReportFragment extends Fragment {

    View view;
    Button temporaryLogin;
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    List<Attendance> attendanceList;

    //private static final String ATTENDANCE_URL = "http://192.168.43.46/attendanceApp/get_students_attendance.php";


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
//        attendanceList = new ArrayList<>();
//        recyclerView = (RecyclerView) view.findViewById(R.id.studentReportRecyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //loadAttendance();

        //temporary intent to login page
//        temporaryLogin = view.findViewById(R.id.temp_login);
//        temporaryLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //when in fragment instead of using ReportFragment.this,  use getActivity() instead
//                Intent intent = new Intent(getActivity(), loginActivity.class);
//                startActivity(intent);
//            }
//        });


    }

//    private void loadAttendance() {
//        //begin
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, ATTENDANCE_URL,
//                new Response.Listener<String>() {  //this method will be executed when there is no error
//                    @Override
//                    public void onResponse(String response) {
//                        //array of data that we'll save from the URL
//                        //first convert to Json array
//                        try {
//                            JSONArray attendances = new JSONArray(response);
//                            //now loop through all items in the array
//                            for (int i = 0; i < attendances.length(); i++) {
//                                JSONObject attendanceObject = attendances.getJSONObject(i);
//
//                                String student_firstName = attendanceObject.getString("first_name");
//                                String student_lastName = attendanceObject.getString("last_name");
//                                String student_presence = attendanceObject.getString("Present_no");
//                                String student_name = student_firstName +" " +student_lastName;
//
//                                //now create course object
//                                Attendance attendance = new Attendance(student_name, student_presence);
//
//                                //add this course to the course list
//                                attendanceList.add(attendance);
//                            }
//
//                            studentAdapter = new StudentAdapter(getContext(), attendanceList);
//                            recyclerView.setAdapter(studentAdapter);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) { //when we have errors
//                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
//
//                    }
//                });
//
//        Volley.newRequestQueue(requireContext()).add(stringRequest);
//        //end
//    }

}