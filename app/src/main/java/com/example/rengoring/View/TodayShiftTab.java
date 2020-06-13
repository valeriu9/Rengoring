package com.example.rengoring.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rengoring.R;
import com.example.rengoring.ViewModel.TodayShiftTabViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayShiftTab extends Fragment {


    private TodayShiftTabViewModel todayShiftTabViewModel;
    private Button startShift;
    private Button endShift;
    private Switch aSwitch;
    private EditText location;
    private String savedLocation;
    private boolean savedSwitch;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    public TodayShiftTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        setViewModel();
        View v = inflater.inflate(R.layout.layout_today_shift, container, false);
        location = (EditText) v.findViewById(R.id.locationEditText);
        startShift = (Button) v.findViewById(R.id.checkIn);
        endShift = (Button) v.findViewById(R.id.checkOut);
        aSwitch = (Switch) v.findViewById(R.id.switch1);
        startShift.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                todayShiftTabViewModel.startShift(location.getText().toString());
                saveData();
                location.setText("");
            }
        });
        endShift.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                todayShiftTabViewModel.endShift();
                location.setText("");
            }
        });
        //For shared preferences
        loadData();
        updateView();
        return v;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (aSwitch.isChecked()) {
            editor.putString(TEXT, location.getText().toString());
            editor.putBoolean(SWITCH1, aSwitch.isChecked());
            Toast.makeText(getContext(), "Data is saved", Toast.LENGTH_LONG).show();
        }
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        savedLocation = sharedPreferences.getString(TEXT, "");
        savedSwitch = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateView() {
        location.setText(savedLocation);
        aSwitch.setChecked(savedSwitch);
    }

    public void setViewModel() {
        todayShiftTabViewModel = new ViewModelProvider(this).get(TodayShiftTabViewModel.class);
    }


}
