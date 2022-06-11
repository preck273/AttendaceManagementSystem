package com.treehouse.studentattendance;

public class JsonDataList {
    String user_course_name;
    String user_start_time;
    String user_end_time;
    String user_department;

    public JsonDataList() {

    }

    public JsonDataList(String user_course_name, String user_start_time, String user_end_time, String user_department) {
        this.user_course_name = user_course_name;
        this.user_start_time = user_start_time;
        this.user_end_time = user_end_time;
        this.user_department = user_department;
    }

    public String getUser_course_name() {
        return user_course_name;
    }

    public void setUser_course_name(String user_course_name) {
        this.user_course_name = user_course_name;
    }

    public String getUser_start_time() {
        return user_start_time;
    }

    public void setUser_start_time(String user_start_time) {
        this.user_start_time = user_start_time;
    }

    public String getUser_end_time() {
        return user_end_time;
    }

    public void setUser_end_time(String user_end_time) {
        this.user_end_time = user_end_time;
    }

    public String getUser_department() {
        return user_department;
    }

    public void setUser_department(String user_department) {
        this.user_department = user_department;
    }
}
