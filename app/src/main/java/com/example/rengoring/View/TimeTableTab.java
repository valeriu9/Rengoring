package com.example.rengoring.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rengoring.Model.TimeTable;
import com.example.rengoring.R;
import com.example.rengoring.ViewModel.TimeTableTabViewModel;
import com.example.rengoring.adapters.RecyclerViewAdapterTimeTable;

import java.util.List;


public class TimeTableTab extends Fragment {
    private TimeTableTabViewModel timeTableTabViewModel;
    private RecyclerViewAdapterTimeTable adapter;


    public TimeTableTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        setViewModel();
        View v = inflater.inflate(R.layout.fragment_timetable_tab, container, false);

        return v;
    }

    public void setViewModel() {
        timeTableTabViewModel = new ViewModelProvider(this).get(TimeTableTabViewModel.class);
        timeTableTabViewModel.getTimeTables().observe(getViewLifecycleOwner(), new Observer<List<TimeTable>>() {
            @Override
            public void onChanged(List<TimeTable> timeTables) {
                adapter.setTimeTables(timeTables);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_viewTimeTable);
        adapter = new RecyclerViewAdapterTimeTable(timeTableTabViewModel.getTimeTables().getValue(), getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}