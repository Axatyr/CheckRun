package com.example.checkrun.RecyclerView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class CardTrainingDiffCallback extends DiffUtil.Callback{

    private final List<CardTraining> oldCardTrainingList;
    private final List<CardTraining> newCardTrainingList;

    public CardTrainingDiffCallback(List<CardTraining> oldCardTrainingList, List<CardTraining> newCardTrainingList) {
        this.oldCardTrainingList = oldCardTrainingList;
        this.newCardTrainingList = newCardTrainingList;
    }


    @Override
    public int getOldListSize() {
        return oldCardTrainingList.size();
    }

    @Override
    public int getNewListSize() {
        return newCardTrainingList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCardTrainingList.get(oldItemPosition) == newCardTrainingList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final CardTraining oldTraining = oldCardTrainingList.get(oldItemPosition);
        final CardTraining newTraining = newCardTrainingList.get(newItemPosition);

        return oldTraining.getName().equals(newTraining.getName()) &&
                oldTraining.getDate().equals(newTraining.getDate()) &&
                oldTraining.getDescription().equals(newTraining.getDescription()) &&
                oldTraining.getFilePath().equals(newTraining.getFilePath());
    }

    @Nullable
    @Override
    public Object getChangePayload( int oldItemPosition, int newItemPosition) {
        return  super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
