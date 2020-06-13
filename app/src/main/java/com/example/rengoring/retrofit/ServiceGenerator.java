package com.example.rengoring.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://rengoring-86901.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static TimeTableApi timeTableApi = retrofit.create(TimeTableApi.class);

    public static TimeTableApi getTimeTableApi() {
        return timeTableApi;
    }
}

