package com.example.asus.myapp.Teacher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.Class.Msg;
import com.example.asus.myapp.R;
import com.example.asus.myapp.Student.StudentActivity;
import com.example.asus.myapp.Student.Update_Student_Activity;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Msg_Edit_Activity extends AppCompatActivity {

    private EditText title,msg;
    private Button update;
    String x;
    private FirebaseAuth auth;
    String idinfo;
    Firebase firebase;
    DatabaseReference mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__edit_);

        Bundle bundle = getIntent().getExtras();
        x=bundle.getString("id");
        title =(EditText) findViewById(R.id.title);
        msg = (EditText) findViewById(R.id.msg);
        update = (Button) findViewById(R.id.update);
        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Info");
        Firebase.setAndroidContext(this);


        final DatabaseReference current_info = mdatabase.child(x);

        current_info.child("type").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 =(String) dataSnapshot.getValue();
                title.setText(value1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_info.child("info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2 =(String) dataSnapshot.getValue();
                msg.setText(value2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String t = title.getText().toString().trim();
                final String m = msg.getText().toString().trim();

                final DatabaseReference current_info = mdatabase.child(x);

                current_info.child("type").setValue(t);
                current_info.child("info").setValue(m);


                Toast.makeText(Msg_Edit_Activity.this, "Updated!" ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Msg_Edit_Activity.this, Show_Message_Activity.class));
                finish();
            }
        });

    }
}
