package com.example.asus.myapp.Class;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by ASUS on 10/12/2016.
 */

public class FireApp extends Application {

   @Override
    public void onCreate(){
       super.onCreate();
       Firebase.setAndroidContext(this);
   }
}
