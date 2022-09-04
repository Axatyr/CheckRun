package com.example.checkrun.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;

public class CardTrainingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nameTextView;
    TextView dateTextView;
    TextView distanceTextView;
    TextView timeTextView;

    private OnItemListener itemListener;

    public CardTrainingViewHolder(@NonNull View itemView, OnItemListener listener) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.activityName);
        dateTextView = itemView.findViewById(R.id.activityDate);
        distanceTextView = itemView.findViewById(R.id.activityDistance);
        timeTextView = itemView.findViewById(R.id.activityTime);
        itemListener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemListener.onItemClick(getAdapterPosition());
    }
}
