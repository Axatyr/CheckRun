package com.example.checkrun.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.checkrun.RecyclerView.CardTraining;

import java.util.List;

public class CardTrainingRepository {

    private final CardTrainingDAO cardTrainingDAO;
    private final LiveData<List<CardTraining>> cardTrainingList;

    public CardTrainingRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        cardTrainingDAO = db.cardTrainingDAO();
        cardTrainingList = cardTrainingDAO.getCardTrainings();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<CardTraining>> getCardTrainingList() {
        return cardTrainingList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void addCardTraining(CardTraining cardTraining) {
        AppDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
                cardTrainingDAO.addCardTraining(cardTraining);
            }
        });
    }
}
