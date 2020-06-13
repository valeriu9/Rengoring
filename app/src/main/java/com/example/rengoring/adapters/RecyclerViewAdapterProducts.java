package com.example.rengoring.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rengoring.Model.Supply;
import com.example.rengoring.R;

import java.util.ArrayList;

public class RecyclerViewAdapterProducts extends RecyclerView.Adapter<RecyclerViewAdapterProducts.ViewHolder> {
    private static final String TAG = "RecycleViewAdapterRoom";
    private ArrayList<Supply> supplies;
    private Context context;

    public RecyclerViewAdapterProducts(ArrayList<Supply> supplies, Context context) {
        this.supplies = supplies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.productName.setText(supplies.get(position).getName());
        holder.quantity.setText(Integer.toString(supplies.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (supplies != null) {
            return supplies.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView productName;
        TextView quantity;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textView);
            quantity = itemView.findViewById(R.id.textQuantity);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}