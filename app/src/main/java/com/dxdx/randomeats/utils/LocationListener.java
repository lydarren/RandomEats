package com.dxdx.randomeats.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import com.dxdx.randomeats.ui.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;

public class LocationListener extends LiveData<Location> {

    public static final int LOCATION_REQ = 10;
    private static LocationListener instance;
    private Context context;

    private LocationListener(Context c){
        this.context = c;
        init();
    }
    public static LocationListener getInstance(Context c){
        if(instance == null){
            instance = new LocationListener(c);
        }
        return instance;
    }

    private LocationRequest getLocationRequest(){
        LocationRequest r = new LocationRequest();
        r.setInterval(10);
        r.setNumUpdates(1);
        r.setFastestInterval(5);
        r.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return r;
    }

    private LocationCallback getLocationCallback(){
        LocationCallback mCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location l = locationResult.getLastLocation();
                setValue(l);
            }
            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {

            }
        };
        return mCallback;
    }

    private void init(){
        LocationRequest r = getLocationRequest();
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        LocationCallback mCallback = getLocationCallback();
        int permOne = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permTwo = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if((permOne == PackageManager.PERMISSION_GRANTED)
                || permTwo == PackageManager.PERMISSION_GRANTED){
            client.requestLocationUpdates(r, mCallback, null);
        }
    }
}
