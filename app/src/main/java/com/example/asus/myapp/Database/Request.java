package com.example.asus.myapp.Database;

/**
 * Created by nur on 12/27/16.
 */

public class Request {
    String id;
    String studentid;
    String classroomid;
    String accepted;

    public Request(){
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {this.id=id;}

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid=studentid;
    }

    public String getClassroomid() {
        return classroomid;
    }

    public void setClassroomid(String classroomid) {this.classroomid=classroomid;}

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted=accepted;
    }


}
