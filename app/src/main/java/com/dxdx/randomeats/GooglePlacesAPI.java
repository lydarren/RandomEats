package com.dxdx.randomeats;

import com.dxdx.randomeats.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GooglePlacesAPI {

    @GET("businesses/search")
    Call<List<Place>> getPlaces();

    @GET("json?placeid=/{id}")
    Call<Place> getPlaceDetails(@Path("id") int id);
}
