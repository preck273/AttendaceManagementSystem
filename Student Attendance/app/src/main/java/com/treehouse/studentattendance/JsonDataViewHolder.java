package com.treehouse.studentattendance;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JsonDataViewHolder extends RecyclerView.ViewHolder {

    TextView courseName;
    TextView teacherName;
    EditText startTime;
    EditText endTime;

    public JsonDataViewHolder( View itemView) {
        super(itemView);

        courseName = itemView.findViewById(R.id.courseName);
        teacherName = itemView.findViewById(R.id.course_teacher_name);
        startTime = itemView.findViewById(R.id.startTime_editText);
        endTime = itemView.findViewById(R.id.endTime_editText);
    }
}
