package com.example.rengoring.Model;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTable {
    private String location;
    private String startTime;
    private String endTime;
    private boolean check = true;

    public TimeTable(String location, String startTime, String endTime, Boolean check) {
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.check = check;
    }

    public String getLocation() {
        return location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd hh:mm");
        this.startTime = df.format(c);
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd hh:mm");
        this.endTime = df.format(c);
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @NonNull
    @Override
    public String toString() {
        return getLocation() + "" + getStartTime() + " " + getEndTime() + " " + isCheck();
    }
}
