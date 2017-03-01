package com.example.asus.myapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.myapp.Database.Classroom;
import com.example.asus.myapp.Database.students;
import com.example.asus.myapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by nur on 12/28/16.
 */

public class StudentAdapter  extends ArrayAdapter<String> {

    private DatabaseReference mdatabase;
    private FirebaseAuth auth;

    public StudentAdapter(Context context, ArrayList<String> notify)
    {
        super(context, 0, notify);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         final String st = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_list, parent, false);
        }
        final TextView name = (TextView) convertView.findViewById(R.id.stname);
        final TextView roll = (TextView) convertView.findViewById(R.id.roll);
        final TextView phn = (TextView) convertView.findViewById(R.id.phn);



        mdatabase= FirebaseDatabase.getInstance().getReference();
        final DatabaseReference current_user_db = mdatabase.child("Students").child(st);



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
                String value2 =(String) dataSnapshot.getValue();
                phn.setText(value2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return convertView;
    }
}
