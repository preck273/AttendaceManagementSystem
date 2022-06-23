package com.treehouse.studentattendance;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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

    //constructor
    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.the_course_layout,  parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        // the holder instance variable (of the CourseViewHolder class below
        // holds the UI element and with the position var,
        //we can get the specified item from the list.
        Course course = courseList.get(position);

        holder.textViewTitle.setText(course.getCourseName());
        holder.textViewStartDate.setText(course.getStartDate());
        holder.textViewEndDate.setText(course.getEndDate());

    }

    @Override
    public int getItemCount() { // return the size of the list : courseList
        return courseList.size(); // if you return 0, the recycler view will not display any items.
    }

    //viewHolder class created inside the adapter class
    class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewStartDate, textViewEndDate;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.course_layout_title_TV);
            textViewStartDate = itemView.findViewById(R.id.course_layout_startDate_TV);
            textViewEndDate = itemView.findViewById(R.id.course_layout_endDate_TV);
        }
    }
}
