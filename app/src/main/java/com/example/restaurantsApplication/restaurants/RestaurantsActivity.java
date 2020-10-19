package com.example.restaurantsApplication.restaurants;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.restaurantsApplication.R;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setAllowEnterTransitionOverlap(false);
    }
}
