package com.example.restaurantsApplication.restaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.restaurantsApplication.server.RestaurantsService;
import com.example.restaurantsApplication.server.ServerProvider;
import com.example.restaurantsApplication.util.ItemAdapter;
import com.example.restaurantsApplication.R;
import com.example.restaurantsApplication.model.Item;
import com.example.restaurantsApplication.restaurantdetails.RestaurantDetailsActivity;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RestaurantsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ArrayList<Item> items;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        initViews();

        boolean isConnected = checkInternetConnection();
        if(isConnected) {
            getData();
        } else {
            showDialog();
        }
    }

    private void initViews(){
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void getData() {
        RestaurantsService restaurantsService = ServerProvider.createRestaurantsService();
        Call<ArrayList<Item>> call = restaurantsService.getItems();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if(!response.isSuccessful())
                    return;

                items = new ArrayList<>();
                items = response.body();

                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    setAdapter();
                });
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                t.getMessage();
            }
        });

    }

    private void setAdapter(){
        ItemAdapter itemAdapter = new ItemAdapter(items, getBaseContext());
        itemAdapter.setOnClickListener(position -> {
            Item item = items.get(position);
            Intent i = new Intent(RestaurantsActivity.this, RestaurantDetailsActivity.class);
            i.putExtra(RestaurantDetailsActivity.ITEM_KEY, item);
            startActivity(i);
        });

        recyclerView.setAdapter(itemAdapter);
    }

    private boolean checkInternetConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED;
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.info_dialog);
        AppCompatTextView exitTextView = dialog.findViewById(R.id.exit_textView);
        exitTextView.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        dialog.show();
    }
}
