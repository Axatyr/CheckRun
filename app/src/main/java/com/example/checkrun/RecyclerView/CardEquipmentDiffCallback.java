package com.example.checkrun.RecyclerView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class CardEquipmentDiffCallback extends DiffUtil.Callback {

    private final List<CardEquipment> oldCardEquipmentList;
    private final List<CardEquipment> newCardEquipmentList;

    public CardEquipmentDiffCallback(List<CardEquipment> oldCardEquipmentList, List<CardEquipment> newCardEquipmentList) {
        this.oldCardEquipmentList = oldCardEquipmentList;
        this.newCardEquipmentList = newCardEquipmentList;
    }

    @Override
    public int getOldListSize() {
        return oldCardEquipmentList.size();
    }

    @Override
    public int getNewListSize() {
        return newCardEquipmentList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCardEquipmentList.get(oldItemPosition) == newCardEquipmentList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final CardEquipment oldEquipment = oldCardEquipmentList.get(oldItemPosition);
        final CardEquipment newEquipment = newCardEquipmentList.get(newItemPosition);

        return oldEquipment.getName().equals(newEquipment.getName()) &&
                oldEquipment.getDate().equals(newEquipment.getDate()) &&
                oldEquipment.getCurrDistance() == newEquipment.getCurrDistance() &&
                oldEquipment.getMaxDistance() == newEquipment.getMaxDistance();
    }

    @Nullable
    @Override
    public Object getChangePayload( int oldItemPosition, int newItemPosition) {
        return  super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
