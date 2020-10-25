package com.example.restaurantsApplication.server;

import com.example.restaurantsApplication.model.Item;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantsService {

    @GET("/restaurant/list")
    Call<ArrayList<Item>> getItems();
}
