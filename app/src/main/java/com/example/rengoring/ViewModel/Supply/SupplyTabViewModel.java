package com.example.rengoring.ViewModel.Supply;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rengoring.Model.Request;
import com.example.rengoring.repositories.RequestRepository;

import java.util.ArrayList;

public class SupplyTabViewModel extends ViewModel {
    private RequestRepository requestRepository;

    public SupplyTabViewModel() {
        requestRepository = RequestRepository.getInstance();
    }

    public LiveData<ArrayList<Request>> getRequests() {
        return requestRepository.getRequests();
    }

    public void removedRequest(int position) {
        requestRepository.removeRequest(position);
    }

}
