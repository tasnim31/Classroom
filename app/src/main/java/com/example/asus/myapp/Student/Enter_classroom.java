package com.example.asus.myapp.Student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.Database.Request;
import com.example.asus.myapp.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Enter_classroom extends AppCompatActivity {
    Button apply;
    private TextView tname;
    private TextView name;
    private TextView tintitution;
    private TextView institution;
    private TextView tcourse_no;
    private TextView course_no;
    private TextView tcourse_title,tphn,phn;
    private TextView course_title;
    private ImageView picture;
    private String shpic;
    String x;
    FirebaseAuth auth;
    private DatabaseReference req,teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_classroom);
        apply=(Button)findViewById(R.id.apply);
        Bundle bundle = getIntent().getExtras();
        x=bundle.getString("id");
        auth = FirebaseAuth.getInstance();
        picture=(ImageView) findViewById(R.id.pic);
        shpic="dasj";
        tname = (TextView) findViewById(R.id.tname);
        name = (TextView) findViewById(R.id.name);
        tintitution = (TextView) findViewById(R.id.tins);
        institution = (TextView) findViewById(R.id.institution);
        tcourse_no = (TextView) findViewById(R.id.tCourse_no);
        course_no = (TextView) findViewById(R.id.Course_no);
        tcourse_title = (TextView) findViewById(R.id.tCourse_title);
        course_title = (TextView) findViewById(R.id.Course_title);
        tphn = (TextView) findViewById(R.id.tphn);
        phn = (TextView)findViewById(R.id.phn);

        req = FirebaseDatabase.getInstance().getReference().child("Request");
        teacher = FirebaseDatabase.getInstance().getReference().child("Teacher");
        Firebase.setAndroidContext(this);


        DatabaseReference current_user_db = teacher.child(x);


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

        current_user_db.child("course_no").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value3 =(String) dataSnapshot.getValue();
                course_no.setText(value3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("course_title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value4 =(String) dataSnapshot.getValue();
                course_title.setText(value4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value5 =(String) dataSnapshot.getValue();
                phn.setText(value5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toast.makeText(Enter_classroom.this,x,Toast.LENGTH_LONG).show();
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference New =req.push();
                Request request=new Request();
                request.setId(New.getKey());
                request.setClassroomid(x);
                request.setStudentid(auth.getCurrentUser().getUid());
                request.setAccepted("0");
                New.setValue(request);

                Intent intent=new Intent(Enter_classroom.this,Apply_classroom.class);
                startActivity(intent);
            }
        });
    }
}