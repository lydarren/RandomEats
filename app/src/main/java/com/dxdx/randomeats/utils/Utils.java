package com.dxdx.randomeats.utils;

import com.dxdx.randomeats.utils.GooglePlacesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";

    public static Retrofit getRetrofit(){
        Retrofit r = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return r;
    }

    public static GooglePlacesAPI getRetrofitService(Retrofit r){
        return r.create(GooglePlacesAPI.class);
    }
}
