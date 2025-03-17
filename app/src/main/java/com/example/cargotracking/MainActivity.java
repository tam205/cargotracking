package com.example.cargotracking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartTracking = findViewById(R.id.btnStartTracking);
        Button btnStopTracking = findViewById(R.id.btnStopTracking);

        // Check and request permissions
        if (!checkLocationPermission()) {
            requestLocationPermission();
        }

        btnStartTracking.setOnClickListener(v -> {
            startTracking();
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });

        btnStopTracking.setOnClickListener(v -> stopTracking());
    }

    private boolean checkLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE
        );
    }

    @SuppressLint("NewApi")
    private void startTracking() {
        Intent intent = new Intent(this, LocationTrackingService.class);
        startForegroundService(intent);
    }

    private void stopTracking() {
        Intent intent = new Intent(this, LocationTrackingService.class);
        stopService(intent);
    }
}