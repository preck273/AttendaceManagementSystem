package com.treehouse.studentattendance;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class CourseFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    ArrayList<JsonDataList> courseArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_course, container, false);
        return view;
    }

    //initialize view here
    @Override
    public void onViewCreated(@NonNull  View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.courseRecyclerView);

        //recycler view
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //use getContext() instead of CourseFragment.this when in fragment

        courseArrayList = new ArrayList<JsonDataList>();

        JsonFetch jsonFetch = new JsonFetch();

    }

    public class JsonFetch extends AsyncTask<String,String,String>{

        HttpURLConnection  httpURLConnection = null;
        String mainFile;

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL("http://localhost/attendanceApp/get_attendance_detail.php");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                String line;
                while((line = bufferedReader.readLine()) != null){

                    stringBuffer.append(line);
                }

                mainFile = stringBuffer.toString();

                JSONArray parent = new JSONArray(mainFile);
                int i = 0;
                while(i <= parent.length()){

                    JSONObject child = parent.getJSONObject(i);

                    String courseName = child.getString("courseName");
                    String startTime = child.getString("startTime");
                    String endTime = child.getString("endTime");
                    String departmentID = child.getString("departmentID");

                    courseArrayList.add(new JsonDataList(courseName, startTime, endTime, departmentID));
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JsonAdapter jsonAdapter = new JsonAdapter(courseArrayList, getContext());
            recyclerView.setAdapter(jsonAdapter);
        }
    }
}