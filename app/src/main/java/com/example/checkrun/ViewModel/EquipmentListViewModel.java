package com.example.checkrun.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.checkrun.Database.CardEquipmentRepository;
import com.example.checkrun.RecyclerView.CardEquipment;

import java.util.List;

public class EquipmentListViewModel extends AndroidViewModel {

    private final MutableLiveData<CardEquipment> equipmentSelected = new MutableLiveData<>();
    public LiveData<List<CardEquipment>> cardEquipments;

    public EquipmentListViewModel(@NonNull Application application) {
        super(application);
        CardEquipmentRepository repository = new CardEquipmentRepository(application);
        cardEquipments = repository.getCardEquipmentList();
    }

    public MutableLiveData<CardEquipment> getEquipmentSelected() {
        return equipmentSelected;
    }

    public void setEquipmentSelected(CardEquipment cardEquipment) {
        equipmentSelected.setValue(cardEquipment);
    }

    public LiveData<List<CardEquipment>> getCardEquipments() { return cardEquipments;}
}
