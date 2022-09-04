package com.example.checkrun.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;

import java.util.List;

public class CardTrainingAdapter extends RecyclerView.Adapter<CardTrainingViewHolder> {

    private List <CardTraining> cardTrainingsList;
    private Activity activity;
    private OnItemListener listener;

    public CardTrainingAdapter(OnItemListener listener, List<CardTraining> cartTrainingsList, Activity activity) {
        this.cardTrainingsList = cartTrainingsList;
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardTrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_training, parent, false);
        return new CardTrainingViewHolder(layoutView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTrainingViewHolder holder, int position) {
        CardTraining currentCardTraining = cardTrainingsList.get(position);

        holder.dateTextView.setText(currentCardTraining.getDate());
        holder.nameTextView.setText(currentCardTraining.getName());
        holder.distanceTextView.setText(String.valueOf(currentCardTraining.getDistance()));
        holder.timeTextView.setText(currentCardTraining.getTime());
    }

    @Override
    public int getItemCount() {
        return cardTrainingsList.size();
    }
}
