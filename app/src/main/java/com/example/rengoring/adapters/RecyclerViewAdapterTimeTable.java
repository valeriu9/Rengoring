package com.example.rengoring.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rengoring.Model.TimeTable;
import com.example.rengoring.R;

import java.util.List;

public class RecyclerViewAdapterTimeTable extends RecyclerView.Adapter<RecyclerViewAdapterTimeTable.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private List<TimeTable> timeTables;
    private Context context;

    public RecyclerViewAdapterTimeTable(List<TimeTable> timeTables, Context context) {
        this.timeTables = timeTables;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_timetable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        System.out.println(timeTables.get(position) + " arrived in adapter");
        holder.location.setText(timeTables.get(position).getLocation());
        holder.startTime.setText(timeTables.get(position).getStartTime());
        holder.endTime.setText(timeTables.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        if (timeTables != null) {
            return timeTables.size();
        }
        return 0;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView location;
        private TextView startTime;
        private TextView endTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.locationId);
            startTime = itemView.findViewById(R.id.startTimeId);
            endTime = itemView.findViewById(R.id.endTimeId);
        }
    }
}

