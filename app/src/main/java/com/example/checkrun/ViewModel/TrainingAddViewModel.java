package com.example.checkrun.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.checkrun.Database.CardEquipmentRepository;
import com.example.checkrun.Database.CardTrainingRepository;
import com.example.checkrun.RecyclerView.CardEquipment;
import com.example.checkrun.RecyclerView.CardTraining;

import java.util.List;

public class TrainingAddViewModel extends AndroidViewModel {
    private final CardTrainingRepository repository;
    private final CardEquipmentRepository repositoryEquipment;
    private final LiveData<List<String>> equipmentsName;

    public TrainingAddViewModel(@NonNull Application application) {
        super(application);
        repository = new CardTrainingRepository(application);
        repositoryEquipment = new CardEquipmentRepository(application);
        equipmentsName = repositoryEquipment.getEquipmentName();
    }

    public void addCardTraining(CardTraining training) { repository.addCardTraining(training);}

    public LiveData<List<String>> getEquipmentName() { return equipmentsName;}

    public void sumDistanceEquipment(float newDistance, String name) { repositoryEquipment.sumDistanceEquipment(newDistance, name);}
}
