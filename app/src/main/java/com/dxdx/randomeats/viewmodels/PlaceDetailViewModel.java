package com.dxdx.randomeats;

import android.util.Log;

import com.dxdx.randomeats.models.Place;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceDetailViewModel extends ViewModel {
    private static final String TAG = "PlaceDetailViewModel";
    private PlaceDetailRepository mRepo;
    private MutableLiveData<Place> mPlace;

    public PlaceDetailViewModel(){
        mRepo = PlaceDetailRepository.getInstance();
        mPlace = mRepo.getRandomPlace();
    }

    public LiveData<Place> getPlace(){
        Log.d(TAG, "getPlace: ");
        return mPlace;
    }

    public void getNextPlace(){
        mRepo.getRandomPlace();
    }
}
