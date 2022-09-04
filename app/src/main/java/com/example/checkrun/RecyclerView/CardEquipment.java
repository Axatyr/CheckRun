package com.example.checkrun.RecyclerView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipment")
public class CardEquipment {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "equipment_id")
    private int id;

    @ColumnInfo(name="Name")
    private final String name;
    @ColumnInfo(name="MaxDistance")
    private final float maxDistance;
    @ColumnInfo(name="CurrDistance")
    private final float currDistance;
    @ColumnInfo(name="Date")
    private final String date;

    public CardEquipment(String name, float maxDistance, float currDistance, String date) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.currDistance = currDistance;
        this.date = date;
    }

    public String getName() {return name;}
    public float getMaxDistance() {return maxDistance;}
    public float getCurrDistance() {return currDistance;}
    public String getDate() {return date;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
