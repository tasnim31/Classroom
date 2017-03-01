package com.example.asus.myapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.myapp.Database.Classroom;
import com.example.asus.myapp.R;

import java.util.ArrayList;

/**
 * Created by nur on 12/27/16.
 */

public class ClassroomAdapter extends ArrayAdapter<Classroom> {


    public ClassroomAdapter(Context context, ArrayList<Classroom> notify)
    {
        super(context, 0, notify);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Classroom course = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.classroom_list, parent, false);
        }
        final TextView clssname = (TextView) convertView.findViewById(R.id.clssname);
        final TextView cno = (TextView) convertView.findViewById(R.id.cno);
        final TextView cname = (TextView) convertView.findViewById(R.id.cname);


        clssname.setText(course.getTeachername());
        cno.setText(course.getCourse_no());
        cname.setText(course.getCourse_name());
        return convertView;

    }
}
