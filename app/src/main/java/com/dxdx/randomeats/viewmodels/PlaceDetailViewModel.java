package com.dxdx.randomeats.viewmodels;

import com.dxdx.randomeats.models.Result;
import com.dxdx.randomeats.data.repositories.PlaceDetailRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceDetailViewModel extends ViewModel {

    private PlaceDetailRepository mRepo;
    private MutableLiveData<Result> mPlace;
    private MutableLiveData<Boolean> mIsUpdating;

    public PlaceDetailViewModel(){
        mRepo = PlaceDetailRepository.getInstance();
        mPlace = mRepo.getRandomPlace();
        mIsUpdating = new MutableLiveData<>();
    }

    public void initViewModel(String location, String rad){
        mIsUpdating.setValue(true);
        mRepo.populateResultsCache(location, rad);
        mIsUpdating.postValue(false);
    }

    public LiveData<Result> getPlace(){
        return mPlace;
    }

    public void getNextPlace(){
        mRepo.getRandomPlace();
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
