package com.example.checkrun.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.checkrun.RecyclerView.CardTraining;

import java.util.ArrayList;
import java.util.List;

public class TrainingListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardTraining> trainingSelected = new MutableLiveData<>();

    public MutableLiveData<List<CardTraining>> cardTrainings;

    public TrainingListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<CardTraining> getTrainingSelected() {
        return trainingSelected;
    }

    public void setTrainingSelected(CardTraining cardTraining) {
        trainingSelected.setValue(cardTraining);
    }

    public LiveData<List<CardTraining>> getCardTrainings() {
        if(cardTrainings == null) {
            cardTrainings = new MutableLiveData<>();
            loadItems();
        }
        return cardTrainings;
    }

    private void loadItems() {
        addCardTraining(new CardTraining("nome", "descrizione", "percorso", 10, 10, "02022022", "Pegasus"));
        addCardTraining(new CardTraining("nome1", "descrizione1", "percorso1", 15, 20, "02022022", "Pegasus"));
        addCardTraining(new CardTraining("nome2", "descrizione2", "percorso2", 12, 30, "02-02-2022", "Speedgoat"));
    }

    public void addCardTraining(CardTraining training) {
        ArrayList<CardTraining> list = new ArrayList<>();
        list.add(training);
        if(cardTrainings.getValue() != null) {
            list.addAll(cardTrainings.getValue());
        }
        cardTrainings.setValue(list);
    }

}
