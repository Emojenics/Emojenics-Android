package com.example.imojen.imojnicsapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SplashActivity splash=this;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        boolean isloggedin = pref.getBoolean("login",false);//("login", true);
        if(!isloggedin) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(splash, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }, 2000); // 2 seconds
        }
        else
        {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(splash, MainActivity.class);
                    intent.putExtra("slection", "1");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }, 2000); // 2 seconds
        }


    }
}
