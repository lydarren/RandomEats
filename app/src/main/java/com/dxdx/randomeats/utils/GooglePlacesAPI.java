package com.dxdx.randomeats.utils;

import com.dxdx.randomeats.models.response.PlacesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesAPI {
    @GET("nearbysearch/json?")
    Call<PlacesResponse> getPlaces(@Query("location") String latlng,
                                 @Query("radius") String rad, @Query("type") String type,
                                 @Query("keyword") String word, @Query("key") String key);

    @GET("details/json?")
    Call<PlacesResponse> getPlaceDetails(@Query("placeid") String id, @Query("fields") String fields, @Query("key") String key);
}
