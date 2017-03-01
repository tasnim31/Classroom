package com.example.asus.myapp.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.myapp.Class.Info_adapter;
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

public class See_Info extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference mdatabase;
    ArrayList<String> infos;
    String idinfo;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see__info);

        Bundle bundle=getIntent().getExtras();
        idinfo=bundle.getString("id");

        listView=(ListView)findViewById(R.id.list);
        infos=new ArrayList<String>();

        firebaseAuth=FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Info");

        mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object> newpost=(Map<String,Object>) dataSnapshot.getValue();
                String id=String.valueOf(newpost.get("teacher"));
                if(id.equalsIgnoreCase(idinfo))
                {
                    infos.add(dataSnapshot.getKey());
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

    public void setlist()
    {
        Info_adapter info_adapter=new Info_adapter(See_Info.this,infos);
        listView.setAdapter(info_adapter);
    }
}
