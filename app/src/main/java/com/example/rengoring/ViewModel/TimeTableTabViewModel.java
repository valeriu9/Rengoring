package com.example.rengoring.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.rengoring.Model.TimeTable;
import com.example.rengoring.repositories.TimeTableRepository;
import java.util.List;

public class TimeTableTabViewModel extends AndroidViewModel {
    private TimeTableRepository timeTableRepository;

    public TimeTableTabViewModel(Application application) {
        super(application);
        timeTableRepository = TimeTableRepository.getInstance(application);
    }

    public LiveData<List<TimeTable>> getTimeTables() {
        return timeTableRepository.getTimeTables();
    }
}
