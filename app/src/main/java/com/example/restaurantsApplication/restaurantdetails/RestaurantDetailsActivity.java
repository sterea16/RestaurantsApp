package com.example.restaurantsApplication.restaurantdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import com.example.restaurantsApplication.model.Item;
import com.example.restaurantsApplication.R;
import com.example.restaurantsApplication.model.ItemPhoto;
import com.example.restaurantsApplication.util.SharedPrefsUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public final static String ITEM_KEY = RestaurantDetailsActivity.class.getName() + " ITEM ";
    private String restaurantName;
    private String description;
    private ArrayList<String> photoPathArray;
    private Item restaurant;
    private MenuItem itemMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        restaurant = (Item) getIntent().getSerializableExtra(ITEM_KEY);

        getData();
        setToolbar();
        initViews(savedInstanceState);
    }

    private void getData(){
        restaurantName = restaurant.getTitle();
        description = restaurant.getDescription();
        photoPathArray = getPhotos();
    }

    public ArrayList<String> getPhotos(){
        ArrayList<String> photos = new ArrayList<>();
        ArrayList<ItemPhoto> nestedPhotos = restaurant.getItemPhotos();
        for(int i = 0; i < nestedPhotos.size(); ++i)
            photos.add(i, nestedPhotos.get(i).getImagePath());
        return photos;
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(restaurantName);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void initViews(Bundle savedInstanceState){
        MapView mapView = findViewById(R.id.maps);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mapView.onResume();

        AppCompatTextView titleTextView = findViewById(R.id.details_title_textView);
        AppCompatTextView descriptionTextView = findViewById(R.id.details_description_textView);
        titleTextView.setText(restaurantName);
        descriptionTextView.setText(description);

        RecyclerView recyclerView = findViewById(R.id.details_pictures_recycler_view);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        RestaurantDetailsAdapter itemAdapter = new RestaurantDetailsAdapter(photoPathArray, this);
        recyclerView.setAdapter(itemAdapter);
    }

    private void updateItemIcon(boolean isFavorite) {
        if(isFavorite){
            itemMenu.setIcon(R.drawable.ic_favorite_24px);
        } else {
            itemMenu.setIcon(R.drawable.ic_favorite_border_24px);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coordinates = new LatLng(restaurant.getLatitude(),restaurant.getLongitude());
        final float defZoom = 15f;
        googleMap.addMarker(new MarkerOptions().position(coordinates));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, defZoom));
        googleMap.setOnMarkerClickListener(marker -> {
            marker.setTitle(restaurantName);
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        itemMenu = menu.findItem(R.id.action_favorite);

        String key = restaurantName;
        boolean isFavorite = new SharedPrefsUtil(this).getFile().getBoolean(key, false);
        updateItemIcon(isFavorite);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_favorite){
            String key = restaurantName;

            SharedPrefsUtil prefs = new SharedPrefsUtil(this);
            boolean isFavorite = prefs.getFile().getBoolean(key, false);
            isFavorite = !isFavorite;

            prefs.getEditor().putBoolean(restaurantName, isFavorite).apply();
            updateItemIcon(isFavorite);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}