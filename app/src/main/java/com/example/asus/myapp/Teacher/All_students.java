package com.example.asus.myapp.Teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asus.myapp.Class.StudentAdapter;
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

public class All_students extends AppCompatActivity {

    ListView listView;
    String clss;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    ArrayList<String> sts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        listView=(ListView)findViewById(R.id.list);
        auth = FirebaseAuth.getInstance();

        sts=new ArrayList<String>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Request");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                String clsrm=String.valueOf(newPost.get("classroomid"));
                String st=String.valueOf(newPost.get("studentid"));
                String acc=String.valueOf(newPost.get("accepted"));

                if(clsrm.equalsIgnoreCase(auth.getCurrentUser().getUid()) && acc.equalsIgnoreCase("1"))
                {
                    sts.add(st);
                }

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

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
        StudentAdapter studentAdapter=new StudentAdapter(All_students.this,sts);
        listView.setAdapter(studentAdapter);
    }
}
