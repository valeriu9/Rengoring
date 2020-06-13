package com.example.rengoring.Model;

import java.util.ArrayList;

public class TimeTableList {
    private ArrayList<TimeTable> timeTable;

    public TimeTableList(ArrayList<TimeTable> timeTable) {
        this.timeTable = timeTable;
    }

    public ArrayList<TimeTable> getTimeTable() {
        return timeTable;
    }
}
