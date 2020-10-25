package com.example.restaurantsApplication.restaurantdetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.restaurantsApplication.util.ItemAdapter;
import com.example.restaurantsApplication.R;

import java.util.Objects;

public class RestaurantDetailsActivity extends AppCompatActivity {
    public final static String TITLE_KEY = RestaurantDetailsActivity.class.getName() + " TITLE ";
    public final static String DESCRIPTION_KEY = RestaurantDetailsActivity.class.getName() + " DESCRIPTION ";
    public final static String PHOTO_ARRAY_KEY = RestaurantDetailsActivity.class.getName() + " PHOTOS ARRAY ";
    private String title;
    private String description;
    private String[] photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        title = getIntent().getStringExtra(TITLE_KEY);
        description = getIntent().getStringExtra(DESCRIPTION_KEY);
        photos = getIntent().getStringArrayExtra(PHOTO_ARRAY_KEY);

        initViews();
    }

    private void initViews(){
        AppCompatTextView titleTextView = findViewById(R.id.details_title_textView);
        titleTextView.setText(title);
        AppCompatTextView descriptionTextView = findViewById(R.id.details_description_textView);
        descriptionTextView.setText(description);

        RecyclerView recyclerView = findViewById(R.id.details_pictures_recycler_view);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ItemAdapter itemAdapter = new ItemAdapter(photos, this, true);
        itemAdapter.setViewHolderLayoutProvider(() -> R.layout.item_photo);
        recyclerView.setAdapter(itemAdapter);
    }

}