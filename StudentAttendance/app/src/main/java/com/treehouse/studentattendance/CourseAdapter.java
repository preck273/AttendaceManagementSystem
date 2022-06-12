package com.treehouse.studentattendance;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView.Adapter - binds the data to the view
 * RecyclerView.ViewHolder -  holds the view
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
//we place the viewHolder inside the <> above, since it is inside Adapter class, you first
// have to mention the parent class : CourseAdapter, then the subclass: CourseViewHolder

    private Context context; // needed to inflate the course layout
    private List<Course> courseList;

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //viewHolder class created inside the adapter class
    class CourseViewHolder extends RecyclerView.ViewHolder{

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
