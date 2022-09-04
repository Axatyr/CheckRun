package com.example.checkrun.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.checkrun.RecyclerView.CardEquipment;

import java.util.List;

@Dao
public interface CardEquipmentDAO {

    //The selected OnConflictStrategy ignores a new CardItem if it's already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCardEquipment(CardEquipment cardEquipment);

    // @Transaction: anything inside the method runs in a single transaction.
    @Transaction
    @Query("SELECT * FROM equipment ORDER BY equipment_id DESC")
    LiveData<List<CardEquipment>> getCardEquipments();
}
