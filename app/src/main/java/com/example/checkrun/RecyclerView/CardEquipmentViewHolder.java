package com.example.checkrun.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;

public class CardEquipmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView dateEquipmentTextView;
    TextView nameEquipmentTextView;
    ProgressBar distanceEquipmentBar;

    private OnItemListener itemListener;

    public CardEquipmentViewHolder(@NonNull View itemView, OnItemListener listener) {
        super(itemView);
        dateEquipmentTextView = itemView.findViewById(R.id.dateTextEquipment);
        nameEquipmentTextView = itemView.findViewById(R.id.equipmentText);
        distanceEquipmentBar = itemView.findViewById(R.id.distanceBar);
        itemListener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) { itemListener.onItemClick(getAdapterPosition());}
}