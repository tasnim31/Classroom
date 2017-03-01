package com.example.asus.myapp.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.myapp.Database.Classroom;
import com.example.asus.myapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TeacherRegistrationActivity extends AppCompatActivity {

    private EditText inputEmail, inputName, inputInstitution, inputCourse_no, inputCourse_title, inputPassword , phone;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mdatabase;
    private DatabaseReference mclassroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);

        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Teacher");
        mclassroom = FirebaseDatabase.getInstance().getReference().child("Classroom");

        phone = (EditText)findViewById(R.id.Tphn);
        inputName = (EditText) findViewById(R.id.TRname);
        inputInstitution = (EditText) findViewById(R.id.TRinstitution);
        inputCourse_no = (EditText) findViewById(R.id.TRcourse_no);
        inputCourse_title =(EditText) findViewById(R.id.TRcourse_title);
        inputEmail = (EditText) findViewById(R.id.TRemail);
        inputPassword = (EditText) findViewById(R.id.TRpassword);
        btnSignUp = (Button) findViewById(R.id.TRsign_up_button);
        progressBar = (ProgressBar) findViewById(R.id.TRprogressBar);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = inputName.getText().toString().trim();
                final String institution = inputInstitution.getText().toString().trim();
                final String course_no = inputCourse_no.getText().toString().trim();
                final String course_tilte = inputCourse_title.getText().toString().trim();
                final String phn = phone.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(institution)) {
                    Toast.makeText(getApplicationContext(), "Enter institution!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(course_no)) {
                    Toast.makeText(getApplicationContext(), "Enter course no.!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(course_tilte)) {
                    Toast.makeText(getApplicationContext(), "Enter course title!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phn)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TeacherRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(TeacherRegistrationActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (!task.isSuccessful()) {
                                                    Toast.makeText(TeacherRegistrationActivity.this, "Authentication failed." + task.getException(),
                                                            Toast.LENGTH_SHORT).show();
                                                } else if (task.isSuccessful()) {
                                                    String user_id = auth.getCurrentUser().getUid();
                                                    DatabaseReference current_user_db = mdatabase.child(user_id);
                                                    current_user_db.child("uid").setValue(user_id);
                                                    current_user_db.child("name").setValue(name);
                                                    current_user_db.child("institution").setValue(institution);
                                                    current_user_db.child("course_no").setValue(course_no);
                                                    current_user_db.child("course_title").setValue(course_tilte);
                                                    current_user_db.child("phone").setValue(phn);

                                                    DatabaseReference clssdb = mclassroom.child(user_id);
                                                    Classroom classroom = new Classroom();
                                                    classroom.setCourse_no(course_no);
                                                    classroom.setCourse_name(course_tilte);
                                                    classroom.setTeachername(name);
                                                    classroom.setTeacherid(auth.getCurrentUser().getUid());
                                                    classroom.setId(user_id);

                                                    clssdb.setValue(classroom);

                                                    startActivity(new Intent(TeacherRegistrationActivity.this, TeacherLoginActivity.class));
                                                    finish();
                                                }
                                            }
                                        });


                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}