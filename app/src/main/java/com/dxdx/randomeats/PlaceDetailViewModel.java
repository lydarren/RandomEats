package com.dxdx.randomeats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceDetailViewModel extends ViewModel {


    PlaceDetailRepository mRepo;
    MutableLiveData<Place> mPlace;

    public PlaceDetailViewModel(){
        mRepo = PlaceDetailRepository.getInstance();

    }

    public LiveData<Place> getPlace(){
        return mPlace;
    }

    public void getNextPlace(){
        mPlace = mRepo.getNextPlace();
    }

}
