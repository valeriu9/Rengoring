package com.example.rengoring.View.Request;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.rengoring.Model.Request;
import com.example.rengoring.R;
import com.example.rengoring.ViewModel.Supply.SupplyTabViewModel;
import com.example.rengoring.adapters.RecyclerViewAdapterRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestTab extends Fragment implements RecyclerViewAdapterRequest.OnListItemClickListener {

    private SupplyTabViewModel supplyTabViewModel;
    private RecyclerViewAdapterRequest adapter;

    public RequestTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_supply_tab, container, false);
        setViewModel();
        return v;
    }

    public void setViewModel() {
        supplyTabViewModel = new ViewModelProvider(this).get(SupplyTabViewModel.class);
        supplyTabViewModel.getRequests().observe(getViewLifecycleOwner(), new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> supplies) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapterRequest(supplyTabViewModel.getRequests().getValue(), getActivity(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        onClickListenerFAB(view);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("clicked", "clicked Delete");
        supplyTabViewModel.removedRequest(clickedItemIndex);
        adapter.notifyDataSetChanged();
    }

    private void onClickListenerFAB(View view) {
        FloatingActionButton myFab = view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

