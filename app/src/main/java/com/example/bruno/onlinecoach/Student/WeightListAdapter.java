package com.example.bruno.onlinecoach.Student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bruno.onlinecoach.Objects.Measure;
import com.example.bruno.onlinecoach.R;

import java.util.List;

public class WeightListAdapter extends RecyclerView.Adapter<WeightListAdapter.ViewHolder> {

    private List<Measure> mWeightList;

    public WeightListAdapter(List<Measure> mWeightList) {
        this.mWeightList = mWeightList;
    }

    @NonNull
    @Override
    public WeightListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View weightView = inflater.inflate(R.layout.item_weight_list, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(weightView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WeightListAdapter.ViewHolder viewHolder, int i) {

        Measure snapshot = mWeightList.get(i);

        // Set item views based on your views and data model
        TextView tv_date = viewHolder.date;
        tv_date.setText(snapshot.getDate().toString());

        TextView tv_weight = viewHolder.weight;
        tv_weight.setText(String.valueOf(snapshot.getWeight()));

        TextView tv_fat = viewHolder.fat;
        tv_fat.setText(String.valueOf(snapshot.getFat()));

        TextView tv_muscle = viewHolder.muscle;
        tv_muscle.setText(String.valueOf(snapshot.getMuscle()));

    }

    @Override
    public int getItemCount() {
        return mWeightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView date, weight, fat, muscle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            weight = (TextView) itemView.findViewById(R.id.tv_weight);
            fat = (TextView) itemView.findViewById(R.id.tv_fat);
            muscle = (TextView) itemView.findViewById(R.id.tv_muscle);
        }
    }
}
