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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.R;
import com.example.asus.myapp.Student.StudentLoginActivity;
import com.example.asus.myapp.UI.ResetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherLoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnLogin, btnRegister, btnReset;
    private TextView tvsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        auth = FirebaseAuth.getInstance();
        tvsLogin = (TextView) findViewById(R.id.tvtLogin);
        btnLogin = (Button) findViewById(R.id.TLsign_in_button);
        btnRegister =(Button) findViewById(R.id.TLsign_up_button);
        inputEmail = (EditText) findViewById(R.id.TLemail);
        inputPassword = (EditText) findViewById(R.id.TLpassword);
        progressBar = (ProgressBar) findViewById(R.id.TLprogressBar);
        btnReset = (Button) findViewById(R.id.TLbtn_reset_password);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(TeacherLoginActivity.this, TeacherRegistrationActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TeacherLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        Toast.makeText(TeacherLoginActivity.this,  "Authentication failed." + task.getException(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if(user.isEmailVerified()) {
                                    Intent intent = new Intent(TeacherLoginActivity.this, TeacherAreaActivity.class);
                                    startActivity(intent);
                                    finish();}
                                    else{
                                        Toast.makeText(TeacherLoginActivity.this, "please verify email, then login again", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(TeacherLoginActivity.this, TeacherLoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
