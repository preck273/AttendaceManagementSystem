package com.treehouse.studentattendance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class ProfileFragment extends Fragment {

    public static final String TAG = MainActivity.class.getSimpleName();

    View view;
    TextView firstName, lastName, email, type;
    List<User> userList;

    private static final String USER_URL = "http://10.0.2.2/attendanceApp/get_profile_detail.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize views here
        firstName = view.findViewById(R.id.user_name_TV);
        lastName = view.findViewById(R.id.user_lastName_TV);
        email = view.findViewById(R.id.user_email);
        type = view.findViewById(R.id.user_type);

        userList = new ArrayList<>();


        loadUser();
        Log.d(TAG, "onViewCreate:  userlistB var = " + userList);

//        firstName.setText(student.getFirstName());
//        lastName.setText(student.getLastName());
//        email.setText(student.getEmail());
//        type.setText(student.getType());

    }


    //get courses from URL
    private void loadUser() {
        //begin
        StringRequest stringRequest = new StringRequest(Request.Method.GET, USER_URL,
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
                                String type = "student";

                                //now create course object
                               User student = new User(firstName, lastName, email, type);

                               //add student to list
                                userList.add(student);
                                Log.d(TAG, "onViewCreate:  userList A var = " + userList);


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