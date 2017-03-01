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

public class Show_Message_Activity extends AppCompatActivity {

    private RecyclerView msgList;
    private DatabaseReference mdata;
    private FirebaseAuth auth;
    public Button edit,delete;
    private com.google.firebase.database.Query mquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__message_);

        auth= FirebaseAuth.getInstance();
        String current_user= auth.getCurrentUser().getUid();
        mdata= FirebaseDatabase.getInstance().getReference().child("Info");
        mquery=mdata.orderByChild("teacher").equalTo(current_user);
        msgList = (RecyclerView)findViewById(R.id.msg_list);
        msgList.setHasFixedSize(true);
        msgList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Msg,MsgViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Msg, MsgViewHolder>(
                Msg.class,
        R.layout.msg_row,
        MsgViewHolder.class,
        mquery
        ) {

            @Override
            protected void populateViewHolder(MsgViewHolder viewHolder, Msg model, int position) {

                final String info_key = getRef(position).getKey();

                viewHolder.setType(model.getType());
                viewHolder.setInfo(model.getInfo());
                viewHolder.setTime(model.getTime());

                viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Show_Message_Activity.this,Msg_Edit_Activity.class);
                        intent.putExtra("id",info_key);
                        startActivity(intent);
                    }
                });

                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Show_Message_Activity.this,Msg_Delete_Activity.class);
                        intent.putExtra("id",info_key);
                        startActivity(intent);
                    }
                });
            }
        };

        msgList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MsgViewHolder extends RecyclerView.ViewHolder{

        View mview;
        Button edit,delete;

        public MsgViewHolder(View itemView) {
            super(itemView);

            mview=itemView;

             edit = (Button) mview.findViewById(R.id.edit);
             delete= (Button) mview.findViewById(R.id.delete);

        }


        public void setType(String type){
            TextView title = (TextView) mview.findViewById(R.id.title);
            title.setText(type);

        }

        public void setTime(String time){
            TextView ti = (TextView) mview.findViewById(R.id.time);
            ti.setText(time);

        }


        public void setInfo(String info){
            TextView msg = (TextView) mview.findViewById(R.id.msg);
            msg.setText(info);

        }
    }
    public boolean onCreatOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
