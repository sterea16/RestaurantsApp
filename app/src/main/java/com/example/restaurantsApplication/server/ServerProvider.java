package com.example.restaurantsApplication.server;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerProvider {
    public static final String BASE_URL = "https://3e29d658-1b69-4f01-9ed1-9fe20db44940.mock.pstmn.io";

    public static RestaurantsService createRestaurantsService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return retrofit.create(RestaurantsService.class);
    }


    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = getHttpLoggingInterceptor();
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
    private ServerProvider(){
    }
}
