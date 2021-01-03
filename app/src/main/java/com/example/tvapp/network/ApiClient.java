package com.example.tvapp.network;

import com.example.tvapp.utilities.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    public static final String BASE_URL = "https://www.episodate.com/api/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit =  new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } return retrofit;
    }
}
