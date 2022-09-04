package com.example.checkrun.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;

import java.util.ArrayList;
import java.util.List;

public class CardTrainingAdapter extends RecyclerView.Adapter<CardTrainingViewHolder> {

    private List <CardTraining> cardTrainingsList = new ArrayList<>();
    private Activity activity;
    private OnItemListener listener;

    private List<CardTraining> cardTrainingListNotFiltered = new ArrayList<>();

    public CardTrainingAdapter(OnItemListener listener, Activity activity) {
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

        holder.nameTextView.setText(currentCardTraining.getName());
        holder.dateTextView.setText(currentCardTraining.getDate());
        holder.distanceTextView.setText(String.valueOf(currentCardTraining.getDistance()));
        holder.timeTextView.setText(String.valueOf(currentCardTraining.getTime()));
    }

    @Override
    public int getItemCount() {
        return cardTrainingsList.size();
    }

    public void setData(List<CardTraining> list) {
        this.cardTrainingsList = new ArrayList<>(list);
        this.cardTrainingListNotFiltered = new ArrayList<>(list);

        final CardTrainingDiffCallback diffCallback = new CardTrainingDiffCallback(this.cardTrainingsList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
    }

    public CardTraining getItemSelected(int position) {
        return cardTrainingsList.get(position);
    }
}
