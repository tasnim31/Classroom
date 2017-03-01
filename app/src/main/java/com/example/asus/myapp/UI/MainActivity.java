package com.example.asus.myapp.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.myapp.R;
import com.example.asus.myapp.Student.StudentLoginActivity;
import com.example.asus.myapp.Teacher.TeacherLoginActivity;


public class MainActivity extends AppCompatActivity {

    private Button Sbtn;
    private Button Tbtn;
    private TextView tvWelcome;
    String something = "sfdsdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sbtn = (Button) findViewById(R.id.Sbtn);
        Tbtn = (Button) findViewById(R.id.Tbtn);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);

        Sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bStudent = new Intent(MainActivity.this, StudentLoginActivity.class);
                MainActivity.this.startActivity(bStudent);
            }
        });

        Tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bTeacher = new Intent(MainActivity.this, TeacherLoginActivity.class);
                //  bTeacher.putExtra("x",something);
                MainActivity.this.startActivity(bTeacher);
            }
        });


    }
}

