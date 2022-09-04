package com.example.checkrun.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.checkrun.RecyclerView.CardEquipment;
import com.example.checkrun.RecyclerView.CardTraining;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CardTraining.class, CardEquipment.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

        public abstract CardTrainingDAO cardTrainingDAO();
        public abstract CardEquipmentDAO cardEquipmentDAO();

        ///Singleton instance to retrieve when the db is needed
        private static volatile AppDatabase INSTANCE;

        static final ExecutorService executor = Executors.newFixedThreadPool(4);

        static AppDatabase getDatabase(final Context context){
            if (INSTANCE == null){
                //The synchronized is to prevent multiple instances being created.
                synchronized (AppDatabase.class) {
                    //If the db has not yet been created, the builder creates it.
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        AppDatabase.class, "checkrun_database")
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
}
