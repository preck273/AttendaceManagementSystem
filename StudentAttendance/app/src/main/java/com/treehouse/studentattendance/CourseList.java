package com.treehouse.studentattendance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class CourseList extends Fragment {

    View view;

    RecyclerView recyclerView;
    CourseAdapter adapter;

    List<Course> listOfCourses;

    private static final String COURSE_LIST_URL = "http://192.168.43.46/attendanceApp/get_course_detail.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize view here
        listOfCourses = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.courseList_RV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // this set the layout vertical


        loadCourses();

    }

    //get courses from URL
    private void loadCourses() {
        //begin
        StringRequest stringRequest = new StringRequest(Request.Method.GET, COURSE_LIST_URL,
                new Response.Listener<String>() {  //this method will be executed when there is no error
                    @Override
                    public void onResponse(String response) {
                        //array of data that we'll save from the URL
                        //first convert to Json array
                        try {
                            JSONArray courses = new JSONArray(response);
                            //now loop through all items in the array
                            for (int i = 0; i < courses.length(); i++) {
                                JSONObject courseObject = courses.getJSONObject(i);

                                String name = courseObject.getString("course_name");
                                String startDate = courseObject.getString("course_start_date");
                                String endDate = courseObject.getString("course_end_date");

                                //now create course object
                                Course course = new Course(name, startDate, endDate);

                                //add this course to the course list
                                listOfCourses.add(course);
                            }

                            adapter = new CourseAdapter(getContext(), listOfCourses);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { //when we have errors
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        Volley.newRequestQueue(requireContext()).add(stringRequest);
        //end
    }

}