package com.example.rengoring.ViewModel.Supply;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rengoring.Model.Supply;
import com.example.rengoring.repositories.RequestRepository;

import java.util.ArrayList;


public class SupplyProductViewModel extends ViewModel {
    private LiveData<ArrayList<Supply>> productFromRequest;
    private RequestRepository requestRepository;

    public void init(String RequestName) {
        if (productFromRequest != null) {
            return;
        }
        requestRepository = RequestRepository.getInstance();
        productFromRequest = requestRepository.getSupplyByRequestName(RequestName);
    }

    public LiveData<ArrayList<Supply>> getSupplyByRequestName() {
        return productFromRequest;
    }

}
