package com.example.rengoring.retrofit;

import com.example.rengoring.Model.TimeTableList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimeTableApi {
    @GET("/database.json")
    Call<TimeTableList> getTimeTables();
}
