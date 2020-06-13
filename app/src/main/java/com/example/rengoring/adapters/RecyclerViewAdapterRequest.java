package com.example.rengoring.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rengoring.Model.Request;
import com.example.rengoring.R;
import com.example.rengoring.View.Request.ViewSuppliesActivity;

import java.util.ArrayList;

public class RecyclerViewAdapterRequest extends RecyclerView.Adapter<RecyclerViewAdapterRequest.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    final private OnListItemClickListener mOnListItemClickListener;
    private ArrayList<Request> requests;
    private Context context;

    public RecyclerViewAdapterRequest(ArrayList<Request> requests, Context context, OnListItemClickListener listener) {
        this.requests = requests;
        this.context = context;
        this.mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_supply, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.textTitleView.setText(requests.get(position).getName());

        boolean isExpanded = requests.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.viewProducts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewSuppliesActivity.class);
                Request request = requests.get(position);
                request.setExpanded(!request.isExpanded());
                intent.putExtra("productName", request.getName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (requests != null) {
            return requests.size();
        }
        return 0;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTitleView;
        ConstraintLayout expandableLayout;
        RelativeLayout parentLayout;
        Button deleteRequest;
        Button viewProducts;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textTitleView = itemView.findViewById(R.id.titleTextView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            viewProducts = itemView.findViewById(R.id.viewRequest);
            deleteRequest = itemView.findViewById(R.id.deleteRequest);
            deleteRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnListItemClickListener != null) {
                        int position = getAdapterPosition();
                        mOnListItemClickListener.onListItemClick(position);
                    }
                }
            });
            textTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Request request = requests.get(getAdapterPosition());
                    request.setExpanded(!request.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }


}
