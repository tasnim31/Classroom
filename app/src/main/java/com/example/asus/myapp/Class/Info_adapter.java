package com.example.asus.myapp.Class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.myapp.Database.Classroom;
import com.example.asus.myapp.R;
import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.firebase.client.core.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by nur on 1/5/17.
 */

public class Info_adapter extends ArrayAdapter<String> {

    public Info_adapter(android.content.Context context, ArrayList<String> notify)
    {
        super(context, 0, notify);
    }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        String info_id= getItem(position);

        if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.infolist, parent, false);
        }
        final TextView type = (TextView) convertView.findViewById(R.id.type);
        final TextView info = (TextView) convertView.findViewById(R.id.info);
        final TextView time = (TextView) convertView.findViewById(R.id.time);

        DatabaseReference   mdatabase = FirebaseDatabase.getInstance().getReference().child("Info");
        mdatabase.child(info_id).child("type").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type.setText((String)dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    mdatabase.child(info_id).child("info").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            info.setText((String)dataSnapshot.getValue());

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    mdatabase.child(info_id).child("time").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            time.setText((String)dataSnapshot.getValue());

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

        return convertView;

        }


}
