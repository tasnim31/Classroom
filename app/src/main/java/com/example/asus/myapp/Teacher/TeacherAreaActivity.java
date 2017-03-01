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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.R;
import com.example.asus.myapp.Student.StudentActivity;
import com.example.asus.myapp.Student.Update_Student_Activity;
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

public class TeacherAreaActivity extends AppCompatActivity {

    private TextView tname;
    private TextView name;
    private TextView tintitution;
    private TextView institution;
    private TextView tcourse_no;
    private TextView course_no;
    private TextView tcourse_title,tphn,phn;
    private TextView course_title;
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
        setContentView(R.layout.activity_teacher_area);

        upload=(Button)findViewById(R.id.up);
        picture=(ImageView) findViewById(R.id.pic);
        update = (Button) findViewById(R.id.update);

        tname = (TextView) findViewById(R.id.tname);
        name = (TextView) findViewById(R.id.name);
        tintitution = (TextView) findViewById(R.id.tins);
        institution = (TextView) findViewById(R.id.institution);
        tcourse_no = (TextView) findViewById(R.id.tCourse_no);
        course_no = (TextView) findViewById(R.id.Course_no);
        tcourse_title = (TextView) findViewById(R.id.tCourse_title);
        course_title = (TextView) findViewById(R.id.Course_title);
        tphn = (TextView) findViewById(R.id.tphn);
        phn = (TextView)findViewById(R.id.phn);
        auth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Teacher");
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
                Intent update = new Intent(TeacherAreaActivity.this,Update_Teacher_Activity.class);
                TeacherAreaActivity.this.startActivity(update);

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

        current_user_db.child("course_no").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value3 =(String) dataSnapshot.getValue();
                course_no.setText(value3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("course_title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value4 =(String) dataSnapshot.getValue();
                course_title.setText(value4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        current_user_db.child("phone").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value5 =(String) dataSnapshot.getValue();
                phn.setText(value5);
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

                            Toast.makeText(TeacherAreaActivity.this,
                                    "Please select a profile picture less than 3500000 byte" , Toast.LENGTH_LONG).show();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.student_add){
            Intent intent=new Intent(TeacherAreaActivity.this,Add_students.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.student_all){
            Intent intent=new Intent(TeacherAreaActivity.this,All_students.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.msg_all){
            Intent intent=new Intent(TeacherAreaActivity.this,Show_Message_Activity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.msg_add){
            Intent intent=new Intent(TeacherAreaActivity.this,Add_info.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
