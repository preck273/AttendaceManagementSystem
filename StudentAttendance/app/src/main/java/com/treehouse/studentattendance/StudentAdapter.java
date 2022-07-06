package com.treehouse.studentattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    private List<Attendance>attendanceList;

    public StudentAdapter(Context context, List<Attendance> attendanceList) {
        this.context = context;
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.an_attendance,  parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);

        holder.name.setText(attendance.getName());
        holder.presence.setText(attendance.getPresence());
        holder.absence.setText(attendance.getAbsence());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView name, presence, absence;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.reportStudentName);
            presence = itemView.findViewById(R.id.reportStudentPresent);
            absence = itemView.findViewById(R.id.reportStudentAbsent);
        }
    }
}
