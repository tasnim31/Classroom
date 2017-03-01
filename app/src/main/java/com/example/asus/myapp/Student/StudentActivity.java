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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.R;
import com.example.asus.myapp.Teacher.Add_info;
import com.example.asus.myapp.Teacher.Add_students;
import com.example.asus.myapp.Teacher.All_students;
import com.example.asus.myapp.Teacher.Show_Message_Activity;
import com.example.asus.myapp.Teacher.TeacherAreaActivity;
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

public class StudentActivity extends AppCompatActivity {

    private TextView tname;
    private TextView name;
    private TextView tintitution;
    private TextView institution;
    private TextView tyear;
    private TextView year;
    private TextView tterm;
    private TextView term;
    private TextView troll;
    private TextView roll;
    private TextView tphone;
    private TextView phone;
    private FirebaseAuth auth;
    private DatabaseReference mdatabase;
    private Firebase firebase;
    private Button upload,update;
    private String pic,user_id;
    private final int SELECT_PHOTO = 1;
    private ImageView picture;
    private String shpic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        update = (Button) findViewById(R.id.update);
        upload=(Button)findViewById(R.id.up);
        picture=(ImageView) findViewById(R.id.pic);

        tname = (TextView) findViewById(R.id.tname);
        name = (TextView) findViewById(R.id.name);
        tintitution = (TextView) findViewById(R.id.tins);
        institution = (TextView) findViewById(R.id.institution);
        tterm = (TextView) findViewById(R.id.tterm);
        term = (TextView) findViewById(R.id.term);
        tyear = (TextView) findViewById(R.id.tyear);
        year = (TextView) findViewById(R.id.year);
        troll = (TextView) findViewById(R.id.troll);
        roll = (TextView) findViewById(R.id.roll);
        tphone = (TextView) findViewById(R.id.tphone);
        phone = (TextView) findViewById(R.id.phone);
        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Students");
        shpic="dasj";

        user_id = auth.getCurrentUser().getUid();
        DatabaseReference current_user_db = mdatabase.child(user_id);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(StudentActivity.this,Update_Student_Activity.class);
                StudentActivity.this.startActivity(update);

            }
        });

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

        current_user_db.child("year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value3 =(String) dataSnapshot.getValue();
                year.setText(value3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("term").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value4 =(String) dataSnapshot.getValue();
                term.setText(value4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("roll").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value5 =(String) dataSnapshot.getValue();
                roll.setText(value5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value6 =(String) dataSnapshot.getValue();
                phone.setText(value6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.PNG, 100, bos);


                        if (selectedImage.getByteCount() < 1500000) {
                            byte[] img = bos.toByteArray();

                            String encodedImage = Base64.encodeToString(img, Base64.DEFAULT);
                            pic = encodedImage;

                            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            picture.setImageBitmap(decodedByte);
                            DatabaseReference current_user_db = mdatabase.child(user_id);
                            current_user_db.child("propic").setValue(pic);
                        } else
                            Toast.makeText(StudentActivity.this,
                                    "Please select a profile picture less than 3500000 byte" , Toast.LENGTH_LONG).show();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_course,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.all_courses){
            Intent all = new Intent(StudentActivity.this,All_classroom.class);
            StudentActivity.this.startActivity(all);
        }

        if(item.getItemId() == R.id.pending){
            Intent notapp=new Intent(StudentActivity.this,Not_Approved_Classrooms.class);
            startActivity(notapp);
        }

        if(item.getItemId() == R.id.add_courses){
            Intent add = new Intent(StudentActivity.this,Apply_classroom.class);
            StudentActivity.this.startActivity(add);
        }

        return super.onOptionsItemSelected(item);
    }


}
