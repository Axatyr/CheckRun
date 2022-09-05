package com.example.checkrun;

import android.location.Location;

public class GpxNode{

    Location location;
    String ele;
    String time;

    GpxNode(){
        location = null;
        ele = "";
        time = "";
    }

    GpxNode(Location loc){
        location = loc;
        ele = "";
        time = "";
    }

    GpxNode(Location loc, String e, String t){
        location = loc;
        ele = e;
        time = t;
    }

    void setEle(String e){
        ele = e;
    }

    void setTime(String t){
        time = t;
    }

    public Location getLocation(){
        return location;
    }

    public String getLocationString(){
        return location.getLatitude() + ":" + location.getLongitude();
    }

    public String getEle() {
        return ele;
    }

    public String getTime() {
        return time;
    }

    public String getGpsNodeInfo(){
        return location.getLatitude() + ":" + location.getLongitude() + "n"
                + time + "n"
                + ele + "n";
    }
}
