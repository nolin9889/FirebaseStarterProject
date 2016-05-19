package com.software.helloworld;

import com.firebase.client.Firebase;

/**
 * Created by noellin on 5/19/16.
 */
public class FirebaseContext extends android.app.Application {

    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
