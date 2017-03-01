package com.example.asus.myapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.myapp.R;

import java.util.ArrayList;

/**
 * Created by Ultron on 12/29/2016.
 */

public class DoctorAdapter extends ArrayAdapter<doctor> {


    public DoctorAdapter(Context context, ArrayList<doctor> notify)
    {
        super(context, 0, notify);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        doctor Doctor = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_of_list, parent, false);
        }
        final TextView firstname = (TextView) convertView.findViewById(R.id.firstname_list_value);
        final TextView lastname = (TextView) convertView.findViewById(R.id.lastname_list_value);
        final TextView total_review = (TextView) convertView.findViewById(R.id.review_list_value);
        final TextView email = (TextView) convertView.findViewById(R.id.email_list_value);


        firstname.setText(Doctor.getFirstname());
        lastname.setText(Doctor.getLastname());
        email.setText(Doctor.getEmail());
        total_review.setText(Doctor.getTotal_review());
        return convertView;

    }
}