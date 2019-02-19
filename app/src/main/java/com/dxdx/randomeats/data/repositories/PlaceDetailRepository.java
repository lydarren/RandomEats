package com.dxdx.randomeats.data.repositories;

import android.util.Log;

import com.dxdx.randomeats.BuildConfig;
import com.dxdx.randomeats.utils.Utils;
import com.dxdx.randomeats.utils.GooglePlacesAPI;
import com.dxdx.randomeats.models.response.PlacesResponse;
import com.dxdx.randomeats.models.Result;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceDetailRepository {
    private static final String TAG = "PlaceDetailRepository";
    private static PlaceDetailRepository mRepo;
    private String mKey = BuildConfig.placesKey;
    private List<Result> mResults = new ArrayList<>();
    private GooglePlacesAPI mApi;
    private MutableLiveData<Result> mPlace = new MutableLiveData<>();

    private PlaceDetailRepository(GooglePlacesAPI api){
        mApi = api;
        Log.d(TAG, "PlaceDetailRepository: ");
    }

    public void populateResultsCache(String loc, String radius){
        Call<PlacesResponse> call = mApi.getPlaces(loc, radius, "restaurant",
                "food", mKey);
        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                if (response.isSuccessful()) {
                    PlacesResponse re = response.body();
                    mResults = new ArrayList<>(re.getResults());
                    getRandomPlace();
                }
            }
            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });
    }

    private void getPlaceDetail(String placeid){
        Call<PlacesResponse> call = mApi.getPlaceDetails(placeid,
                "name,rating,formatted_phone_number,formatted_address,place_id,types,opening_hours,geometry,url",
                mKey);
        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                if (response.isSuccessful()) {
                    PlacesResponse re = response.body();
                    mPlace.setValue(re.getResult());
                }
            }
            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });
    }

    public static PlaceDetailRepository getInstance(){
        if(mRepo == null){
            mRepo = new PlaceDetailRepository(Utils.getRetrofitService(Utils.getRetrofit()));
        }
        return mRepo;
    }

    public MutableLiveData<Result> getRandomPlace(){
        Log.d(TAG, "getRandomPlace()");
        Random rand = new Random();
        if(mResults != null && mResults.size() > 0){
            int index = rand.nextInt(mResults.size());
            getPlaceDetail(mResults.get(index).getPlaceId());
        }
        return mPlace;
    }
}
