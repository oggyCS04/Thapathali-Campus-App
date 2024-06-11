package com.sagar.thapathaliapp2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.sagar.thapathaliapp2.session.SessionManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                boolean b = sessionManager.checkSession();
                if (b){
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));

                }else{
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                }
            }
        },2000);
    }
}