package com.example.asus.myapp.Teacher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.myapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Approve_students extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    private TextView tname;
    private TextView name;
    private TextView tintitution;
    private TextView institution;
    private TextView tyear;
    private TextView year;
    private TextView tterm;
    private TextView term;
    private TextView troll;
    private TextView roll;
    private TextView tphone;
    private TextView phone;
    private ImageView picture;
    private String shpic;
    private DatabaseReference mdatabase;

    FirebaseAuth firebaseAuth;
    String id;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_students);
        listView=(ListView)findViewById(R.id.list);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Request");
        firebaseAuth=FirebaseAuth.getInstance();
        picture=(ImageView) findViewById(R.id.pic);

        tname = (TextView) findViewById(R.id.tname);
        name = (TextView) findViewById(R.id.name);
        tintitution = (TextView) findViewById(R.id.tins);
        institution = (TextView) findViewById(R.id.institution);
        tterm = (TextView) findViewById(R.id.tterm);
        term = (TextView) findViewById(R.id.term);
        tyear = (TextView) findViewById(R.id.tyear);
        year = (TextView) findViewById(R.id.year);
        troll = (TextView) findViewById(R.id.troll);
        roll = (TextView) findViewById(R.id.roll);
        tphone = (TextView) findViewById(R.id.tphone);
        phone = (TextView) findViewById(R.id.phone);
        shpic="dasj";
        Bundle bundle = getIntent().getExtras();
        id=bundle.getString("id");
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Students");
        button=(Button)findViewById(R.id.apply);

        DatabaseReference current_user_db = mdatabase.child(id);

        current_user_db.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 =(String) dataSnapshot.getValue();
                name.setText(value1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        current_user_db.child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{

                    shpic =(String) dataSnapshot.getValue();
                    byte[] decodedString = Base64.decode(shpic, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    picture.setImageBitmap(decodedByte);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("institution").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2 =(String) dataSnapshot.getValue();
                institution.setText(value2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value3 =(String) dataSnapshot.getValue();
                year.setText(value3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("term").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value4 =(String) dataSnapshot.getValue();
                term.setText(value4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("roll").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value5 =(String) dataSnapshot.getValue();
                roll.setText(value5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value6 =(String) dataSnapshot.getValue();
                phone.setText(value6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, Object> newPost = (Map<String, Object>) dataSnapshot.getValue();
                        String clsrm=String.valueOf(newPost.get("classroomid"));
                        String st=String.valueOf(newPost.get("studentid"));

                        if(clsrm.equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()) && st.equalsIgnoreCase(id))
                        {
                            databaseReference.child(dataSnapshot.getKey()).child("accepted").setValue("1");
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
                        Intent intent=new Intent(Approve_students.this,Add_students.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });






    }
}
