package com.example.checkrun.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.checkrun.RecyclerView.CardEquipment;

import java.util.List;

public class CardEquipmentRepository {

    private final CardEquipmentDAO cardEquipmentDAO;
    private final LiveData<List<CardEquipment>> cardEquipmentList;

    public CardEquipmentRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        cardEquipmentDAO = db.cardEquipmentDAO();
        cardEquipmentList = cardEquipmentDAO.getCardEquipments();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<CardEquipment>> getCardEquipmentList() {
        return cardEquipmentList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void addCardEquipment(CardEquipment cardEquipment) {
        AppDatabase.executor.execute(() -> cardEquipmentDAO.addCardEquipment(cardEquipment));
    }
}
