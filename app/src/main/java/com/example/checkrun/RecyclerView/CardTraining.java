package com.example.checkrun.RecyclerView;

/*
@Entity(tableName = "training",
        foreignKeys = {@ForeignKey(entity = SingleEquipment.class,
                parentColumns = "equipment_id",
                childColumns = "equipment",
                onDelete = ForeignKey.CASCADE)})
 */
public class CardTraining {
    /*
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
    private final String time;
    @ColumnInfo(name="Date")
    private final String date;
    @ColumnInfo(name="Activity")
    private final String activity;
    @ColumnInfo(index = true)
    private final String equipment;
     */

    private final String name;
    private final String description;
    private final String filePath;
    private final float distance;
    private final String time;
    private final String date;
    private final String activity;
    private final String equipment;

    public CardTraining(String name, String description, String filePath, float distance, String time, String date, String activity, String equipment) {
        this.name = name;
        this.description = description;
        this.filePath = filePath;
        this.distance = distance;
        this.time = time;
        this.date = date;
        this.activity = activity;
        this.equipment = equipment;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getFilePath() {return filePath;}
    public float getDistance() {return distance;}
    public String getTime() {return time;}
    public String getDate() {return date;}
    public String getActivity() {return activity;}
    public String getEquipment() {return equipment;}

    /*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     */
}
