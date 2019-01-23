package com.dxdx.randomeats;

import com.dxdx.randomeats.models.Place;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class PlaceDetailRepository {

    private static PlaceDetailRepository mRepo;
    List<Place> mBusinesses;
    MutableLiveData<Place> mPlace;

    private PlaceDetailRepository(){

    }

    public static PlaceDetailRepository getInstance(){
        if(mRepo == null){
            mRepo = new PlaceDetailRepository();
        }
        return mRepo;
    }

    public MutableLiveData<Place> getNextPlace(){
        return mPlace;
    }

}
