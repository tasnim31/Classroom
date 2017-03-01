package com.example.asus.myapp.Student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asus.myapp.Database.students;
import com.example.asus.myapp.R;
import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StudentRegistrationActivity extends AppCompatActivity {

    private EditText inputName, inputYear, inputTerm, inputRoll, inputInstitution, inputPhone, inputEmail, inputPassword;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    Firebase firebase;
    DatabaseReference mdatabase;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Students");
        Firebase.setAndroidContext(this);


        btnSignUp = (Button) findViewById(R.id.SRsign_up_button);
        inputName = (EditText) findViewById(R.id.SRname);
        inputInstitution = (EditText) findViewById(R.id.SRinstitute);
        inputYear = (EditText) findViewById(R.id.SRyear);
        inputTerm = (EditText) findViewById(R.id.SRterm);
        inputRoll = (EditText) findViewById(R.id.SRroll);
        inputPhone = (EditText) findViewById(R.id.SRphone);
        inputEmail = (EditText) findViewById(R.id.SRemail);
        inputPassword = (EditText) findViewById(R.id.SRpassword);
        progressBar = (ProgressBar) findViewById(R.id.SRprogressBar);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = inputName.getText().toString().trim();
                final String institution = inputInstitution.getText().toString().trim();
                final String year = inputYear.getText().toString().trim();
                final String term = inputTerm.getText().toString().trim();
                final String roll =inputRoll.getText().toString().trim();
                final String phone =inputPhone.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter your name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(institution)) {
                    Toast.makeText(getApplicationContext(), "Enter your institution name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(year)) {
                    Toast.makeText(getApplicationContext(), "Enter year!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(term)) {
                    Toast.makeText(getApplicationContext(), "Enter term!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(roll)) {
                    Toast.makeText(getApplicationContext(), "Enter your roll!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter your phone number!", Toast.LENGTH_SHORT).show();
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
                        .addOnCompleteListener(StudentRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(StudentRegistrationActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
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
                                                    Toast.makeText(StudentRegistrationActivity.this, "Authentication failed." + task.getException(),
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                else if(task.isSuccessful()) {
                                                    Toast.makeText(StudentRegistrationActivity.this, "varify email", Toast.LENGTH_SHORT).show();

                                                    String user_id = auth.getCurrentUser().getUid();
                                                    students st = new students();
                                                    st.setId(user_id);
                                                    st.setInstitution(institution);
                                                    st.setName(name);
                                                    st.setRoll(roll);
                                                    st.setPhone(phone);
                                                    st.setTerm(term);
                                                    st.setYear(year);
                                                    DatabaseReference current_user_db = mdatabase.child(user_id);
                                                    current_user_db.setValue(st);
                                                    startActivity(new Intent(StudentRegistrationActivity.this, StudentLoginActivity.class));
                                                    finish();
                                                }
                                            }
                                        });

                            }
                        });

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("StudentRegistration Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}