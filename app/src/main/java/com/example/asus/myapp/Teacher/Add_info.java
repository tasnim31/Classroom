package com.example.asus.myapp.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.myapp.R;
import com.example.asus.myapp.Student.StudentLoginActivity;
import com.example.asus.myapp.Student.StudentRegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Add_info extends AppCompatActivity {

    EditText type,link;
    Button submit;
    FirebaseAuth firebaseAuth;
    DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        type=(EditText)findViewById(R.id.type);

        link=(EditText)findViewById(R.id.link);
        submit=(Button)findViewById(R.id.submit);
        final String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        firebaseAuth=FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Info");



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference infodb=mdatabase.push();
                infodb.child("teacher").setValue(firebaseAuth.getCurrentUser().getUid());
                infodb.child("info").setValue(link.getText().toString());
                infodb.child("type").setValue(type.getText().toString());
                infodb.child("id").setValue(infodb.getKey());
                infodb.child("time").setValue(mydate);
                Toast.makeText(Add_info.this,"Information added",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Add_info.this, TeacherAreaActivity.class));
                finish();
            }
        });

    }
}
