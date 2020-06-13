package com.example.rengoring.ViewModel.Supply;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import com.example.rengoring.Model.Request;
import com.example.rengoring.repositories.RequestRepository;


public class AddRequestViewModel extends AndroidViewModel {
    private RequestRepository requestRepository;

    public AddRequestViewModel(Application application) {
        super(application);
        requestRepository = RequestRepository.getInstance();
    }

    public void addRequest(Request request) {
        requestRepository.addRequest(request);
    }


}
