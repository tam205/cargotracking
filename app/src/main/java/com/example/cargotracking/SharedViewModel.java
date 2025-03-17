package com.example.cargotracking;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<LatLng> currentLocation = new MutableLiveData<>();

    public MutableLiveData<LatLng> getCurrentLocation() {
        return currentLocation;
    }

    public void updateLocation(LatLng latLng) {
        currentLocation.postValue(latLng);
    }
}