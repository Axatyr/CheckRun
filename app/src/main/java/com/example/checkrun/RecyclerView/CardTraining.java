package com.example.checkrun.RecyclerView;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "training")
public class CardTraining {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "training_id")
    private int id;

    @ColumnInfo(name="Name")
    private final String name;
    @ColumnInfo(name="Description")
    private final String description;
    @ColumnInfo(name="FilePath")
    private final String filePath;
    @ColumnInfo(name="Distance")
    private final float distance;
    @ColumnInfo(name="Time")
    private final int time;
    @ColumnInfo(name="Date")
    private final String date;
    @ColumnInfo(name="Equipment")
    private final String equipment;

    public CardTraining(String name, String description, String filePath, float distance, int time, String date, String equipment) {
        this.name = name;
        this.description = description;
        this.filePath = filePath;
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.equipment = equipment;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getFilePath() {return filePath;}
    public float getDistance() {return distance;}
    public int getTime() {return time;}
    public String getDate() {return date;}
    public String getEquipment() {return equipment;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
