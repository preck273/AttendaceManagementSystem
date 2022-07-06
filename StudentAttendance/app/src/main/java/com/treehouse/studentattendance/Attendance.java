package com.treehouse.studentattendance;

public class Attendance {
    private String name, presence, absence;

    public Attendance(String name, String presence, String absence) {
        this.name = name;
        this.presence = presence;
        if(absence == null)
            this.absence = "0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }
}
