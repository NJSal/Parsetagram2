package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("30miDIWsFXH604U5XoO4iUQ3z2ifzW6bSEbuMRBl")
                .clientKey("7oSFS3MiPvUQzYSi9nhkO2m6g77hFztcq36Z5b8d")
                .server("https://parseapi.back4app.com").build());
    }
}
