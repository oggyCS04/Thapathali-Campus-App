package com.sagar.thapathaliapp2.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private final String PREF_FILE_NAME="thapathali";
    private final int PRIVATE_MODE = 0;

    private final String KEY_IF_LOGGED_IN = "key_session_if_logged_in";
    private final String KEY_NAME = "key_session_name";
    private final String KEY_ROLLNO = "key_session_rollno";

    public SessionManager(Context context) {

        this.context = context;
        sp=context.getSharedPreferences(PREF_FILE_NAME,PRIVATE_MODE);
        editor = sp.edit();
    }

    public boolean checkSession(){
        if (sp.contains(KEY_IF_LOGGED_IN)){
            return true;
        }else {
            return false;
        }
    }

    public void createSession(String name, String rollno){

        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ROLLNO, rollno);
        editor.putBoolean(KEY_IF_LOGGED_IN,true);
        editor.commit();
    }

    public String getSessionDetails(String key){
        return sp.getString(key,null);
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }
}
