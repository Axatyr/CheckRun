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

public class CardEquipmentAdapter extends RecyclerView.Adapter<CardEquipmentViewHolder> {

    private List<CardEquipment> cardEquipmentList = new ArrayList<>();
    private Activity activity;
    private OnItemListener listener;

    public CardEquipmentAdapter(OnItemListener listener, Activity activity) {
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_equipment, parent, false);
        return new CardEquipmentViewHolder(layoutView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardEquipmentViewHolder holder, int position) {
        CardEquipment currentCardEquipment = cardEquipmentList.get(position);

        holder.dateEquipmentTextView.setText(currentCardEquipment.getDate());
        holder.nameEquipmentTextView.setText(currentCardEquipment.getName());
        holder.distanceEquipmentBar.setMax((int) currentCardEquipment.getMaxDistance());
        holder.distanceEquipmentBar.setProgress((int) currentCardEquipment.getCurrDistance());
    }

    @Override
    public int getItemCount() {
        return cardEquipmentList.size();
    }

    public void setData(List<CardEquipment> list) {
        this.cardEquipmentList = new ArrayList<>(list);

        final CardEquipmentDiffCallback diffCallback = new CardEquipmentDiffCallback(this.cardEquipmentList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
    }

    public CardEquipment getItemSelected(int position) {
        return cardEquipmentList.get(position);
    }

}
