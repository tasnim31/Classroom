package com.example.asus.myapp.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.myapp.Class.ClassroomAdapter;
import com.example.asus.myapp.Database.Classroom;
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

public class All_classroom extends AppCompatActivity {

    ListView listView;
    ArrayList<Classroom> classrooms;
    ArrayList<String> reqs;
    private DatabaseReference requestdb,mdatabase;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_classroom);
        listView=(ListView)findViewById(R.id.list);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("Classroom");
        reqs=new ArrayList<String>();

        auth = FirebaseAuth.getInstance();

        requestdb = FirebaseDatabase.getInstance().getReference().child("Request");
        classrooms=new ArrayList<Classroom>();
        requestdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                String stuid=String.valueOf(newPost.get("studentid"));

                if(stuid.equalsIgnoreCase(auth.getCurrentUser().getUid()))
                {

                    String st = String.valueOf(newPost.get("classroomid"));
                    String acc = String.valueOf(newPost.get("accepted"));
                    if(acc.equalsIgnoreCase("1"))
                        reqs.add(st);
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

        requestdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mdatabase.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                        String cno = String.valueOf(newPost.get("course_no"));
                        String cname = String.valueOf(newPost.get("course_name"));
                        String teacher = String.valueOf(newPost.get("teachername"));
                        String id = String.valueOf(newPost.get("id"));

                        if(reqs.contains(id)){
                            Classroom classroom=new Classroom();
                            classroom.setCourse_name(cname);
                            classroom.setCourse_no(cno);
                            classroom.setId(id);
                            classroom.setTeachername(teacher);
                            classrooms.add(classroom);
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Classroom classroom=(Classroom) parent.getItemAtPosition(position);
                Intent in=new Intent(All_classroom.this,See_Info.class);
                in.putExtra("id",classroom.getId());
                startActivity(in);

            }
        });


    }


    public void setlist()
    {
        ClassroomAdapter classroomAdapter=new ClassroomAdapter(All_classroom.this,classrooms);
        listView.setAdapter(classroomAdapter);
    }
}
