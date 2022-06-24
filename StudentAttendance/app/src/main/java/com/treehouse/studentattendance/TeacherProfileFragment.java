package com.treehouse.studentattendance;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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


public class TeacherProfileFragment extends Fragment {

    View view;

    private TextView firstName, lastName, email, type;
    List<User> userList;

    private static final String TEACHER_PROFILE_URL = "http://10.0.2.2/attendanceApp/get_profile_detail.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_teacher_profile, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstName = view.findViewById(R.id.teacher_profile_firstName_TV);
        lastName = view.findViewById(R.id.teacher_profile_lastName_TV);
        email = view.findViewById(R.id.teacher_profile_email);
        type = view.findViewById(R.id.teacher_profile_type);

        userList = new ArrayList<>();

        loadTeacher();

    }

    //get courses from URL
    private void loadTeacher() {
        //begin
        StringRequest stringRequest = new StringRequest(Request.Method.GET, TEACHER_PROFILE_URL,
                new Response.Listener<String>() {  //this method will be executed when there is no error
                    @Override
                    public void onResponse(String response) {
                        //array of data that we'll save from the URL
                        //first convert to Json array
                        try {
                            JSONArray user = new JSONArray(response);
                            //now loop through all items in the array
                            for (int i = 0; i < user.length(); i++) {
                                JSONObject courseObject = user.getJSONObject(i);

                                String firstName = courseObject.getString("first_name");
                                String lastName = courseObject.getString("last_name");
                                String email = courseObject.getString("email_address");
                                String type = "teacher";

                                //now create course object
                                User teacher = new User(firstName, lastName, email, type);

                                //add student to list
                                userList.add(teacher);

                            }

                            firstName.setText(userList.get(0).getFirstName());
                            lastName.setText((userList.get(0).getLastName()));
                            email.setText(userList.get(0).getEmail());
                            type.setText(userList.get(0).getType());


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