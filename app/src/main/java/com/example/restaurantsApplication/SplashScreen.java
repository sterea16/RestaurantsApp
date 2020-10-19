package com.example.restaurantsApplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.restaurantsApplication.restaurants.RestaurantsActivity;

/*This is not the best way to create a splash screen because, according to the documentation,
    the recommended solution is by switching themes in main activity
    https://developer.android.com/topic/performance/vitals/launch-time#solutions-3*/
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    /*begin transition when the SplashActivity starts to be visible after about 1.5 seconds*/
    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, RestaurantsActivity.class);
                /*remove the current task together with its activity and add a new task,
                * so when the back button is pressed the app exists instead of returning to splash screen*/
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(SplashScreen.this,
                                                                            android.R.anim.fade_in,
                                                                            android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        };
        handler.postDelayed(runnable, 1500);
    }
}