package com.by5388.sy95306v2.module.guangzhou.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.guangzhou.screen.view.IScreenView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author by5388  on 2018/8/3.
 */

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final List<String> stationNames;
    private final LayoutInflater inflater;
    private final IScreenView view;

    public MyAdapter(@NonNull Context context, @NonNull List<String> stationNames, @NonNull IScreenView view) {
        this.view = view;
        this.inflater = LayoutInflater.from(context);
        this.stationNames = stationNames;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_screen_name, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String name = stationNames.get(position);
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.name.setText(name);
        holder1.name.setTag(position);
        holder1.name.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return stationNames.size();
    }

    @Override
    public void onClick(View v) {
        view.showStationScreen((int) v.getTag());
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final Button name;

        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.button_name);
        }
    }
}