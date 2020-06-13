package com.example.rengoring.repositories;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rengoring.Model.TimeTable;
import com.example.rengoring.Model.TimeTableList;
import com.example.rengoring.retrofit.ServiceGenerator;
import com.example.rengoring.retrofit.TimeTableApi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeTableRepository {
    private static TimeTableRepository instance;
    private MutableLiveData<List<TimeTable>> timeTableListMutableLiveData = new MutableLiveData<>();
    private ArrayList<TimeTable> timeTables = new ArrayList<>();
    Application application;

    public TimeTableRepository(Application application) {
        this.application = application;
    }

    public static TimeTableRepository getInstance(Application application) {
        if (instance == null) {
            instance = new TimeTableRepository(application);
        }
        return instance;
    }

    public LiveData<List<TimeTable>> getTimeTables() {
        TimeTableApi endpoints = ServiceGenerator.getTimeTableApi();

        Call<TimeTableList> call = endpoints.getTimeTables();
        call.enqueue(new Callback<TimeTableList>() {

            @Override
            public void onResponse(Call<TimeTableList> call, Response<TimeTableList> response) {
                TimeTableList apiTimeTables = response.body();
                if (response.isSuccessful()) {
                    timeTableListMutableLiveData.setValue(apiTimeTables.getTimeTable());
                }
            }

            @Override
            public void onFailure(Call<TimeTableList> call, Throwable t) {
                Toast.makeText(application.getBaseContext(), "Unsuccessful connection to server", Toast.LENGTH_LONG).show();
            }
        });
        return timeTableListMutableLiveData;
    }


    public void addShift(TimeTable timeTable) {

        if (timeTables.isEmpty()) {
            timeTables.add(timeTable);
            timeTable.setStartTime();
        } else {
            boolean flag = false;
            for (int i = 0; i < timeTables.size() && !flag; i++) {
                if ((timeTables.get(i).isCheck())) {
                    flag = !flag;
                }
            }
            if (!flag) {
                timeTables.add(timeTable);
                timeTable.setStartTime();
            }
        }
    }


    public void finishShift() {
        for (int i = 0; i < timeTables.size(); i++) {
            if (timeTables.get(i).isCheck()) {
                timeTables.get(i).setEndTime();
                timeTables.get(i).setCheck(false);
                sendToDatabase(timeTables.get(i));
            }
        }
    }

    private void sendToDatabase(TimeTable timeTable) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference;
        int counter = timeTableListMutableLiveData.getValue().size();
        reference = database.getReference("database/timeTable");
        reference.child(String.valueOf(counter)).setValue(timeTable);
    }

}

