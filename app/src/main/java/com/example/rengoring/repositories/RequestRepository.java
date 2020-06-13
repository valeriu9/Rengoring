package com.example.rengoring.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rengoring.Model.Request;
import com.example.rengoring.Model.Supply;

import java.util.ArrayList;

public class RequestRepository {
    private static RequestRepository instance;
    private ArrayList<Request> requestDataSet = new ArrayList<>();
    private ArrayList<Supply> supplyDataSet = new ArrayList<>();
    private ArrayList<Request> supplyByProductNameDataSet = new ArrayList<>();

    public static RequestRepository getInstance() {
        if (instance == null) {
            instance = new RequestRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Request>> getRequests() {
        setRequest();
        MutableLiveData<ArrayList<Request>> data = new MutableLiveData<>();
        data.setValue(requestDataSet);
        return data;
    }

    public LiveData<ArrayList<Supply>> getSupplyByRequestName(String requestName) {
        setRequestByName(requestName);
        MutableLiveData<ArrayList<Supply>> data = new MutableLiveData<>();
        for (Request request : supplyByProductNameDataSet) {
            if (request.getName().equals(requestName)) {
                data.setValue(request.getSupplies());
            }
        }
        return data;
    }


    private void setSupplies() {
        supplyDataSet = new ArrayList<Supply>();
        supplyDataSet.add(new Supply("Gloves XL", 2));
        supplyDataSet.add(new Supply("Rags", 30));
        supplyDataSet.add(new Supply("Mops", 20));
        supplyDataSet.add(new Supply("Toilet solution", 1));
        supplyDataSet.add(new Supply("Round Mops", 5));
        supplyDataSet.add(new Supply("Glass spray", 3));

        supplyDataSet.add(new Supply("Gloves XL", 2));
        supplyDataSet.add(new Supply("Rags", 30));
        supplyDataSet.add(new Supply("Mops", 20));
        supplyDataSet.add(new Supply("Toilet solution", 1));
        supplyDataSet.add(new Supply("Round Mops", 5));
        supplyDataSet.add(new Supply("Glass spray", 3));

        supplyDataSet.add(new Supply("Gloves XL", 2));
        supplyDataSet.add(new Supply("Rags", 30));
        supplyDataSet.add(new Supply("Mops", 20));
        supplyDataSet.add(new Supply("Toilet solution", 1));
        supplyDataSet.add(new Supply("Round Mops", 5));
        supplyDataSet.add(new Supply("Glass spray", 3));
    }

    private void setRequest() {
        requestDataSet = new ArrayList<>();
        setSupplies();
        requestDataSet.add(new Request("First request", supplyDataSet));

        requestDataSet.add(new Request("Second request", supplyDataSet));

        requestDataSet.add(new Request("Third request", supplyDataSet));

        requestDataSet.add(new Request("Forth request", supplyDataSet));

        requestDataSet.add(new Request("Fifth request", supplyDataSet));

        requestDataSet.add(new Request("Seventh request", supplyDataSet));

        requestDataSet.add(new Request("Eight request", supplyDataSet));

        requestDataSet.add(new Request("Nine request", supplyDataSet));

        requestDataSet.add(new Request("tenth request", supplyDataSet));

        requestDataSet.add(new Request("11 request", supplyDataSet));

        requestDataSet.add(new Request("12 request", supplyDataSet));

        requestDataSet.add(new Request("13 request", supplyDataSet));

        requestDataSet.add(new Request("14 request", supplyDataSet));

        requestDataSet.add(new Request("15 request", supplyDataSet));

        requestDataSet.add(new Request("16 request", supplyDataSet));
    }

    private void setRequestByName(String name) {
        setSupplies();

        supplyByProductNameDataSet.add(new Request("First request", supplyDataSet));

        supplyByProductNameDataSet.add(new Request("Second request", supplyDataSet));

        supplyByProductNameDataSet.add(new Request("Third request", supplyDataSet));

        supplyByProductNameDataSet.add(new Request("Forth request", supplyDataSet));

        supplyByProductNameDataSet.add(new Request("Fifth request", supplyDataSet));
    }

    public void removeRequest(int position) {
        requestDataSet.remove(position);
    }

    public void addRequest(Request request) {
        requestDataSet.add(request);
        supplyByProductNameDataSet.add(request);
    }

}
