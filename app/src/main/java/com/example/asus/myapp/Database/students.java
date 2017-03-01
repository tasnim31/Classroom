package com.example.asus.myapp.Database;

/**
 * Created by ASUS on 14/12/2016.
 */

public class students {
    private String id;
    private String name;
    private String institution;
    private String year;
    private String term;
    private String roll;
    private String phone;
    private String pic;

    public students(){

    }

    public students(String name, String institution, String year, String term, String roll, String phone) {
        this.name = name;
        this.institution = institution;
        this.year = year;
        this.term = term;
        this.roll = roll;
        this.phone = phone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic=pic;
    }*/



}