package com.example.rengoring.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.rengoring.Model.TimeTable;
import com.example.rengoring.repositories.TimeTableRepository;


public class TodayShiftTabViewModel extends AndroidViewModel {
    private TimeTableRepository timeTableRepository;

    public TodayShiftTabViewModel(Application application) {
        super(application);
        timeTableRepository = TimeTableRepository.getInstance(application);
    }

    public void startShift(String location) {
        timeTableRepository.addShift(new TimeTable(location, null, null, true));
    }

    public void endShift() {
        timeTableRepository.finishShift();
    }

}
