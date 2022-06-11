package com.treehouse.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JsonAdapter  extends RecyclerView.Adapter<JsonDataViewHolder> {

    ArrayList<JsonDataList> list;
    Context context;

    public JsonAdapter() {
    }

    public JsonAdapter(ArrayList<JsonDataList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public JsonDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.course_row, parent, false);
        return new JsonDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonDataViewHolder holder, int position) {

        JsonDataList currentData = list.get(position);

        holder.courseName.setText(currentData.getUser_course_name());
        //holder.teacherName.setText((currentData.getUser_department()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
