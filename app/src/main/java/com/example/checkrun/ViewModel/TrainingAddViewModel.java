package com.example.checkrun.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.checkrun.Database.CardTrainingRepository;
import com.example.checkrun.RecyclerView.CardTraining;

public class TrainingAddViewModel extends AndroidViewModel {
    private CardTrainingRepository repository;

    public TrainingAddViewModel(@NonNull Application application) {
        super(application);
        repository = new CardTrainingRepository(application);
    }

    public void addCardTraining(CardTraining training) { repository.addCardTraining(training);}
}
