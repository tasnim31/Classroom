package com.example.asus.myapp.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.asus.myapp.Class.DoctorAdapter;
import com.example.asus.myapp.Class.doctor;
import com.example.asus.myapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class list_of_doctor extends AppCompatActivity {
    ListView listView;

    ArrayList<doctor> Doctors;

    private DatabaseReference requestdb, mdatabase;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doctor);
        listView=(ListView)findViewById(R.id.list);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("users").child("Dhaka").child("Gynocologist");
        Doctors=new ArrayList<doctor>();


        mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                String review = String.valueOf(newPost.get("review"));
                String email = String.valueOf(newPost.get(dataSnapshot.getKey()));
                String fn = String.valueOf(newPost.get("firstname"));
                String ln = String.valueOf(newPost.get("lastname"));

                doctor dc=new doctor();
                dc.setEmail(email);
                dc.setFirstname(fn);
                dc.setLastname(ln);
                dc.setTotal_review(review);
                Doctors.add(dc);

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setlist();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    public void setlist()
    {
        DoctorAdapter doctorAdapter=new DoctorAdapter(list_of_doctor.this,Doctors);
        listView.setAdapter(doctorAdapter);
    }
}