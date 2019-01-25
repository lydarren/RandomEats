package com.dxdx.randomeats.viewmodels;

import com.dxdx.randomeats.models.Result;
import com.dxdx.randomeats.data.repositories.PlaceDetailRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceDetailViewModel extends ViewModel {

    private PlaceDetailRepository mRepo;
    private MutableLiveData<Result> mPlace;

    public PlaceDetailViewModel(){
        mRepo = PlaceDetailRepository.getInstance();
        mPlace = mRepo.getRandomPlace();
    }

    public LiveData<Result> getPlace(){
        return mPlace;
    }

    public void getNextPlace(){
        mRepo.getRandomPlace();
    }
}
