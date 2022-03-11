package com.example.instagramclone;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    //description, image, user

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_USER = "user";


    //getter for description: return a string
    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);      //put associates key with description from Parser
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    //setter now
    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    //getter and setter for user who made post
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER,user);
    }
}
