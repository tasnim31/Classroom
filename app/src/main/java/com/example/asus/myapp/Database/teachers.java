package com.example.asus.myapp.Database;

/**
 * Created by ASUS on 16/12/2016.
 */

public class teachers {
    private String name;
    private String institution;
    private String course_no;
    private String course_title;

    public teachers(){

    }

    public teachers(String course_title, String course_no, String institution, String name) {
        this.course_title = course_title;
        this.course_no = course_no;
        this.institution = institution;
        this.name = name;
    }



    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_no() {
        return course_no;
    }

    public void setCourse_no(String course_no) {
        this.course_no = course_no;
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


}
