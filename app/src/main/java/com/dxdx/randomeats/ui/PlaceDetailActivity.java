package com.dxdx.randomeats.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dxdx.randomeats.R;
import com.dxdx.randomeats.models.Result;
import com.dxdx.randomeats.utils.LocationListener;
import com.dxdx.randomeats.viewmodels.PlaceDetailViewModel;

public class PlaceDetailActivity extends AppCompatActivity {
    private static final String TAG = "PlaceDetailActivity";
    private PlaceDetailViewModel mViewModel;

    @BindView(R.id.name_text) TextView mNameText;
    @BindView(R.id.address_text) TextView mAddressText;
    @BindView(R.id.number_text) TextView mNumberText;
    @BindView(R.id.time_text) TextView mTimeText;
    @BindView(R.id.rating_text) TextView mRatingText;
    @BindView(R.id.description_text) TextView mDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);
        initLocationListener();
        initViewModel();
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick(View v){
        mViewModel.getNextPlace();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LocationListener.LOCATION_REQ){
            initLocationListener();
        }
    }

    private void initLocationListener(){
        if(checkLocationPermissions()){
            LocationListener.getInstance(getApplicationContext()).observe(this, new Observer<Location>() {
                @Override
                public void onChanged(Location location) {
                    String loc = location.getLatitude() + "," + location.getLongitude();
                    mViewModel.initViewModel(loc, getIntent().getStringExtra(MainActivity.EXTRA_SPINNER));
                }
            });
        }
    }

    private void initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(PlaceDetailViewModel.class);
        mViewModel.getPlace().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if(result != null){
                    mNameText.setText(result.getName());
                    mAddressText.setText(result.getFormattedAddress());
                    mNumberText.setText(result.getFormattedPhoneNumber());
                    mTimeText.setText(result.getName());
                    mRatingText.setText(String.valueOf(result.getRating()));
                    mDescriptionText.setText(result.getTypes().toString());
                }
            }
        });
    }

    private boolean checkLocationPermissions(){
        int permOne = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permTwo = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if((permOne == PackageManager.PERMISSION_GRANTED)
                || permTwo == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, LocationListener.LOCATION_REQ);
        }
        return false;
    }
}
