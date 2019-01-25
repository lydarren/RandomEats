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
import com.dxdx.randomeats.viewmodels.PlaceDetailViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class PlaceDetailActivity extends AppCompatActivity {
    private static final String TAG = "PlaceDetailActivity";
    public static final int LOCATION_REQ = 10;
    private LocationCallback mCallback;

    @BindView(R.id.name_text)
    TextView mNameText;
    @BindView(R.id.address_text)
    TextView mAddressText;
    @BindView(R.id.number_text)
    TextView mNumberText;
    @BindView(R.id.time_text)
    TextView mTimeText;
    @BindView(R.id.rating_text)
    TextView mRatingText;
    @BindView(R.id.description_text)
    TextView mDescriptionText;

    PlaceDetailViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);

        initViewModel();
        getLocation();
        getIntent().getStringExtra(MainActivity.EXTRA_SPINNER);
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick(View v){
        mViewModel.getNextPlace();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_REQ){
            getLocation();
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

    private void getLocation(){
        LocationRequest r = new LocationRequest();
        r.setInterval(10);
        r.setNumUpdates(1);
        r.setFastestInterval(5);
        r.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        mCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location l = locationResult.getLastLocation();
                String loc = l.getLatitude() + "," + l.getLongitude();
                Log.d(TAG, "onLocationResult: " + loc);
                mViewModel.initViewModel(loc, getIntent().getStringExtra(MainActivity.EXTRA_SPINNER));
            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                Log.d(TAG, "onLocationAvail: ");
            }
        };

        int permOne = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permTwo = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if((permOne == PackageManager.PERMISSION_GRANTED)
                || permTwo == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "Permissions granted");
            client.requestLocationUpdates(r, mCallback, null);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQ);
        }
    }


}
