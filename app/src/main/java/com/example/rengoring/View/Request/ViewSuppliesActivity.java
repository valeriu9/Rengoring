package com.example.rengoring.View.Request;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rengoring.R;
import com.example.rengoring.ViewModel.Supply.SupplyProductViewModel;
import com.example.rengoring.adapters.RecyclerViewAdapterProducts;

public class ViewSuppliesActivity extends AppCompatActivity {
    private static final String TAG = "ViewSupplyActivity";
    private SupplyProductViewModel supplyProductViewModel;
    private RecyclerViewAdapterProducts adapter;
    private String requestName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supply);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            requestName = bundle.getString("productName");
        }
        setViewModel();
    }

    private void setViewModel() {
        supplyProductViewModel = new ViewModelProvider(this).get(SupplyProductViewModel.class);
        supplyProductViewModel.init(requestName);
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_supply);
        adapter = new RecyclerViewAdapterProducts(supplyProductViewModel.getSupplyByRequestName().getValue(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }
}
