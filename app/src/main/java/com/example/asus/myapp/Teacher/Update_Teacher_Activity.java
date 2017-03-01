package com.example.asus.myapp.Teacher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.R;
import com.example.asus.myapp.Student.StudentActivity;
import com.example.asus.myapp.Student.Update_Student_Activity;
import com.example.asus.myapp.Student.delete_Activity;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.example.asus.myapp.R;


public class Update_Teacher_Activity extends AppCompatActivity {

    private EditText nam,inst,c_no,c_title,phone;
    private Button update;
    private FirebaseAuth auth;
    Firebase firebase;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__teacher_);

        nam = (EditText)findViewById(R.id.name);
        inst = (EditText)findViewById(R.id.institute);
        phone = (EditText)findViewById(R.id.phn);
        c_no = (EditText)findViewById(R.id.c_no);
        c_title = (EditText) findViewById(R.id.c_title);
        update = (Button) findViewById(R.id.update);
        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Teacher");
        Firebase.setAndroidContext(this);

        String user_id = auth.getCurrentUser().getUid();
        final DatabaseReference current_user_db = mdatabase.child(user_id);

        current_user_db.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 =(String) dataSnapshot.getValue();
                nam.setText(value1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("institution").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2 =(String) dataSnapshot.getValue();
                inst.setText(value2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("course_no").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value3 =(String) dataSnapshot.getValue();
                c_no.setText(value3);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("course_title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value4 =(String) dataSnapshot.getValue();
                c_title.setText(value4);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value5 =(String) dataSnapshot.getValue();
                phone.setText(value5);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nam.getText().toString().trim();
                final String ins = inst.getText().toString().trim();
                final String phn = phone.getText().toString().trim();
                final String c_n = c_no.getText().toString().trim();
                final String c_titl = c_title.getText().toString().trim();

                String user_id = auth.getCurrentUser().getUid();
                DatabaseReference current_user_db = mdatabase.child(user_id);

                current_user_db.child("name").setValue(name);
                current_user_db.child("institution").setValue(ins);
                current_user_db.child("course_no").setValue(c_n);
                current_user_db.child("course_title").setValue(c_titl);
                current_user_db.child("phone").setValue(phn);

                Toast.makeText(Update_Teacher_Activity.this, "Updated!" ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Update_Teacher_Activity.this, TeacherAreaActivity.class));
                finish();

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Intent all = new Intent(Update_Teacher_Activity.this,delete_Teacher_Activity.class);
            Update_Teacher_Activity.this.startActivity(all);
        }

        return super.onOptionsItemSelected(item);
    }
}
