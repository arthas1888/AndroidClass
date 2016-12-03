package com.example.a68.httpapplication;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String KEY_PREFERENCES = "MyPreferences";
    private static final String KEY_USERNAME = "username";

    @SuppressLint("CommitPrefEdits")
    public MyPreferences(Context context){
        _context = context;
        sharedPreferences = _context
                .getSharedPreferences(KEY_PREFERENCES, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getUsername(){
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public void clearSession(){
        editor.clear();
        editor.commit();
    }
}
