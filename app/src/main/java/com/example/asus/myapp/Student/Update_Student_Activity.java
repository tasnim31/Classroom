package com.example.asus.myapp.Student;

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

public class Update_Student_Activity extends AppCompatActivity {

    private EditText Sname,Sins,Syear,Sterm,Sroll,Sphone;
    private Button Supdate;
    private FirebaseAuth auth;
    Firebase firebase;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student_);

        Sname = (EditText) findViewById(R.id.Sname);
        Sins =(EditText) findViewById(R.id.Sinstitute);
        Syear =(EditText) findViewById(R.id.Syear);
        Sterm =(EditText) findViewById(R.id.Sterm);
        Sroll =(EditText) findViewById(R.id.Sroll);
        Sphone = (EditText) findViewById(R.id.Sphone);
        Supdate = (Button) findViewById(R.id.Supdate);
        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Students");
        Firebase.setAndroidContext(this);

        String user_id = auth.getCurrentUser().getUid();
        final DatabaseReference current_user_db = mdatabase.child(user_id);

        current_user_db.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 =(String) dataSnapshot.getValue();
                Sname.setText(value1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("institution").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2 =(String) dataSnapshot.getValue();
                Sins.setText(value2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value3 =(String) dataSnapshot.getValue();
                Syear.setText(value3);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("term").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value4 =(String) dataSnapshot.getValue();
                Sterm.setText(value4);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("roll").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value5 =(String) dataSnapshot.getValue();
                Sroll.setText(value5);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value6 =(String) dataSnapshot.getValue();
                Sphone.setText(value6);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        Supdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = Sname.getText().toString().trim();
                final String ins = Sins.getText().toString().trim();
                final String year = Syear.getText().toString().trim();
                final String term = Sterm.getText().toString().trim();
                final String roll = Sroll.getText().toString().trim();
                final String phone = Sphone.getText().toString().trim();

                String user_id = auth.getCurrentUser().getUid();
                DatabaseReference current_user_db = mdatabase.child(user_id);

                current_user_db.child("name").setValue(name);
                current_user_db.child("institution").setValue(ins);
                current_user_db.child("year").setValue(year);
                current_user_db.child("term").setValue(term);
                current_user_db.child("roll").setValue(roll);
                current_user_db.child("phone").setValue(phone);

                Toast.makeText(Update_Student_Activity.this, "Updated!" ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Update_Student_Activity.this, StudentActivity.class));
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
            Intent all = new Intent(Update_Student_Activity.this,delete_Activity.class);
            Update_Student_Activity.this.startActivity(all);
        }

        return super.onOptionsItemSelected(item);
    }


}
