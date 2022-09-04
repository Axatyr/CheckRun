package com.example.checkrun.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.checkrun.Database.CardTrainingRepository;
import com.example.checkrun.RecyclerView.CardTraining;

import java.util.List;

public class TrainingListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardTraining> trainingSelected = new MutableLiveData<>();
    public LiveData<List<CardTraining>> cardTrainings;

    public TrainingListViewModel(@NonNull Application application) {
        super(application);
        CardTrainingRepository repository = new CardTrainingRepository(application);
        cardTrainings = repository.getCardTrainingList();
    }

    public MutableLiveData<CardTraining> getTrainingSelected() {
        return trainingSelected;
    }

    public void setTrainingSelected(CardTraining cardTraining) {
        trainingSelected.setValue(cardTraining);
    }

    public LiveData<List<CardTraining>> getCardTrainings() {
        return cardTrainings;
    }
}
