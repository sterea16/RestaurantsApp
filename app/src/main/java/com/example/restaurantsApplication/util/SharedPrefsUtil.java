package com.example.restaurantsApplication.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefsUtil {
    public static final String FILE_NAME = SharedPrefsUtil.class.getName() + " FAVORITES ";
    private final SharedPreferences prefs;

    public SharedPrefsUtil(Context context){
        prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getFile() {
        return prefs;
    }

    public SharedPreferences.Editor getEditor() {
        return prefs.edit();
    }

}
