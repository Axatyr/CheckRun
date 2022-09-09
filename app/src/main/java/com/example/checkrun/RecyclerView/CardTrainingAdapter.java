package com.example.checkrun.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CardTrainingAdapter extends RecyclerView.Adapter<CardTrainingViewHolder> {

    private List <CardTraining> cardTrainingsList = new ArrayList<>();
    private final OnItemListener listener;
    private final Activity activity;

    public CardTrainingAdapter(OnItemListener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
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
        //Distance
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        //Time
        long HH = TimeUnit.MILLISECONDS.toHours(currentCardTraining.getTime());
        long MM = TimeUnit.MILLISECONDS.toMinutes(currentCardTraining.getTime()) % 60;
        long SS = TimeUnit.MILLISECONDS.toSeconds(currentCardTraining.getTime()) % 60;
        String finalTime = String.format("%02d:%02d:%02d", HH, MM, SS);

        holder.nameTextView.setText(currentCardTraining.getName());
        holder.dateTextView.setText(currentCardTraining.getDate());
        holder.distanceTextView.setText(decimalFormat.format(currentCardTraining.getDistance()));
        holder.timeTextView.setText(finalTime);
    }

    @Override
    public int getItemCount() {
        return cardTrainingsList.size();
    }

    public void setData(List<CardTraining> list) {
        this.cardTrainingsList = new ArrayList<>(list);

        final CardTrainingDiffCallback diffCallback = new CardTrainingDiffCallback(this.cardTrainingsList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
    }

    public CardTraining getItemSelected(int position) {
        return cardTrainingsList.get(position);
    }
}
