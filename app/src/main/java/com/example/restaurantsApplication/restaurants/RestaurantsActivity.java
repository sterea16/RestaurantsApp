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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restaurantsApplication.ItemAdapter;
import com.example.restaurantsApplication.R;
import com.example.restaurantsApplication.model.Item;
import com.example.restaurantsApplication.restaurantdetails.RestaurantDetailsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class RestaurantsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RequestQueue queue;
    private ArrayList<Item> items;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        getWindow().setAllowEnterTransitionOverlap(false);
        items = new ArrayList<>();

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_view);

        queue = Volley.newRequestQueue(this);

        boolean isConnected = checkInternetConnection();
        if(isConnected) {
            jsonParse();
        } else {
            showDialog();
        }

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
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
        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private void jsonParse() {
        String URL = "https://80795fb3-e037-4cf4-afc4-2ea225a022b3.mock.pstmn.io/restaurant/list";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject post = response.getJSONObject(i);
                                String name = post.getString("name");
                                String description = post.getString("description");
                                String imagePtah = post.getString("imagePath");
                                JSONArray jsonArray = post.getJSONArray("photos");
                                ArrayList<String> photosList = new ArrayList<>();

                                /*get the photos used in details activity*/
                                for(int k = 0; k < jsonArray.length(); ++k){
                                    JSONObject subPost = jsonArray.getJSONObject(k);
                                    photosList.add(subPost.getString("imagePath"));
                                }

                                String[] photos = photosList.toArray(new String[0]);
                                items.add(new Item(name, description, imagePtah, photos));
                            }

                            setAdapter();
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    private void setAdapter(){
        ItemAdapter itemAdapter = new ItemAdapter(items, getBaseContext(), false);
        itemAdapter.setListener(new ItemAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Item item = items.get(position);
                String title = item.getTitle();
                String description = item.getSubtitle();
                String[] photos = item.getPhotos();

                Intent i = new Intent(RestaurantsActivity.this, RestaurantDetailsActivity.class);
                i.putExtra(RestaurantDetailsActivity.TITLE_KEY, title)
                    .putExtra(RestaurantDetailsActivity.DESCRIPTION_KEY, description)
                    .putExtra(RestaurantDetailsActivity.PHOTO_ARRAY_KEY,photos);
                startActivity(i);
            }
        });

        recyclerView.setAdapter(itemAdapter);
    }
}
