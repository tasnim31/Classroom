package com.example.asus.myapp.Student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.Class.Msg;
import com.example.asus.myapp.R;
import com.example.asus.myapp.Teacher.Msg_Delete_Activity;
import com.example.asus.myapp.Teacher.Show_Message_Activity;
import com.example.asus.myapp.Teacher.delete_Teacher_Activity;
import com.example.asus.myapp.UI.MainActivity;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


import com.example.asus.myapp.R;

public class delete_Activity extends AppCompatActivity {

    private Button del,can;
    String x;
    DatabaseReference mdatabase;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_);

            auth = FirebaseAuth.getInstance();
            x= auth.getCurrentUser().getUid();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            del = (Button) findViewById(R.id.del);
            can = (Button) findViewById(R.id.can);
            mdatabase = FirebaseDatabase.getInstance().getReference().child("Students");
            Firebase.setAndroidContext(this);

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mdatabase.child(x).removeValue();
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(delete_Activity.this, "User account deleted.", Toast.LENGTH_SHORT).show();                                }
                                }
                            });
                    Intent intent=new Intent(delete_Activity.this,MainActivity.class);
                    startActivity(intent);

                }
            });

            can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(delete_Activity.this,Update_Student_Activity.class);
                    startActivity(intent);
                }
            });
        }
}
