package com.example.asus.myapp.Database;

/**
 * Created by nur on 12/27/16.
 */

public class Classroom {
    private String id;
    private String course_name;
    private String course_no;
    private String teacherid;
    private String teachername;




    public Classroom() {
        /**
         * Blank default constructor essential for Firebase
         * */
    }


    //Getters and setters




    public String getId() {

        return id;
    }

    public void setId(String id)
    {

        this.id=id;
    }

    public String getCourse_name() {

        return course_name;
    }

    public void setCourse_name(String course_name)
    {

        this.course_name=course_name;
    }



    public String getCourse_no() {

        return course_no;
    }

    public void setCourse_no(String course_no)
    {

        this.course_no=course_no;
    }


    public String getTeacherid() {

        return teacherid;
    }

    public void setTeacherid(String teacherid)
    {

        this.teacherid=teacherid;
    }

    public String getTeachername() {

        return teachername;
    }

    public void setTeachername(String teachername)
    {

        this.teachername=teachername;
    }



}
