package com.example.asus.myapp.Class;

/**
 * Created by Asus on 1/7/2017.
 */

public class Msg {

    private String type, info, time;

    public Msg(){

    }

    public Msg(String type, String info , String time) {
        this.type = type;
        this.info = info;
        this.time = time;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
