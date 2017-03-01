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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.myapp.Class.Msg;
import com.example.asus.myapp.R;
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


public class Msg_Delete_Activity extends AppCompatActivity {

    private Button del,can;
    String x;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__delete_);

        Bundle bundle = getIntent().getExtras();
        x=bundle.getString("id");
        del = (Button) findViewById(R.id.del);
        can = (Button) findViewById(R.id.can);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Info");
        Firebase.setAndroidContext(this);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdatabase.child(x).removeValue();
                Intent intent=new Intent(Msg_Delete_Activity.this,Show_Message_Activity.class);
                startActivity(intent);

            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Msg_Delete_Activity.this,Show_Message_Activity.class);
                startActivity(intent);
            }
        });
    }
}
