package com.example.asus.myapp.Student;


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
import com.example.asus.myapp.UI.ResetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnLogin, btnRegister, btnReset;
    private TextView tvsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        auth = FirebaseAuth.getInstance();
        tvsLogin = (TextView) findViewById(R.id.tvsLogin);
        btnLogin = (Button) findViewById(R.id.SLsign_in_button);
        btnRegister =(Button) findViewById(R.id.SLsign_up_button);
        inputEmail = (EditText) findViewById(R.id.SLemail);
        inputPassword = (EditText) findViewById(R.id.SLpassword);
        progressBar = (ProgressBar) findViewById(R.id.SLprogressBar);
        btnReset = (Button) findViewById(R.id.SLbtn_reset_password);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLoginActivity.this, StudentRegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
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
                        .addOnCompleteListener(StudentLoginActivity.this, new OnCompleteListener<AuthResult>() {
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
                                        Toast.makeText(StudentLoginActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if(user.isEmailVerified()) {
                                        Intent intent = new Intent(StudentLoginActivity.this, StudentActivity.class);
                                        startActivity(intent);
                                        finish(); }else{
                                        Toast.makeText(StudentLoginActivity.this, "please verify email, then login again", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(StudentLoginActivity.this, StudentLoginActivity.class);
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
