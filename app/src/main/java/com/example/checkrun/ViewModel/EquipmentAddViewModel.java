package com.example.checkrun.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.checkrun.Database.CardEquipmentRepository;
import com.example.checkrun.RecyclerView.CardEquipment;

public class EquipmentAddViewModel extends AndroidViewModel {
    private final CardEquipmentRepository repository;

    public EquipmentAddViewModel(@NonNull Application application) {
        super(application);
        repository = new CardEquipmentRepository(application);
    }

    public void addCardEquipment(CardEquipment equipment) { repository.addCardEquipment(equipment);}
}
