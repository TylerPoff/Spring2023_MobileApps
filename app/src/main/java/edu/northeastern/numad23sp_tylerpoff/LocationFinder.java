package edu.northeastern.numad23sp_tylerpoff;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class LocationFinder extends AppCompatActivity {
    private TextView currentLatitude;
    private TextView currentLongitude;
    private TextView distanceTraveled;
    private Button btnResetDistance;
    private FusedLocationProviderClient fusedLocationClient;
    private double latitudeValue;
    private double longitudeValue;
    private double distanceTraveledValue;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for(Location locationLoop : locationResult.getLocations()) {
                    distanceTraveledValue += currentLocation.distanceTo(locationLoop) / 1000;
                    currentLocation = locationLoop;
                    distanceTraveled.setText(String.format("%.4f",distanceTraveledValue) + " km");
                    latitudeValue = locationLoop.getLatitude();
                    longitudeValue = locationLoop.getLongitude();
                    currentLatitude.setText("Latitude: " + latitudeValue);
                    currentLongitude.setText("Longitude: " + longitudeValue);
                }
            }
        };
        createLocationRequest();
        getLastLocation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        distanceTraveled = findViewById(R.id.distanceValueTV);
        if (savedInstanceState == null) {
            distanceTraveledValue = 0;
        }
        currentLatitude = findViewById(R.id.currentLatitudeTV);
        currentLongitude = findViewById(R.id.currentLongitudeTV);
        btnResetDistance = findViewById(R.id.btnResetDistance);
        btnResetDistance.setOnClickListener(v -> resetDistance());
        startLocationUpdates();
    }

    private void resetDistance() {
        distanceTraveledValue = 0;
        distanceTraveled.setText(distanceTraveledValue + " km");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("distance", distanceTraveledValue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        distanceTraveledValue = savedInstanceState.getDouble("distance");
        distanceTraveled.setText(String.format("%.4f",distanceTraveledValue) + " km");
    }


    @Override
    public void onBackPressed() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to leave?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            super.onBackPressed();
        }
    }

    public void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                currentLocation = location;
                latitudeValue = location.getLatitude();
                longitudeValue = location.getLongitude();
                currentLatitude.setText("Latitude: " + latitudeValue);
                currentLongitude.setText("Longitude: " + longitudeValue);
            } else {
                currentLatitude.setText("Latitude: ");
                currentLongitude.setText("Longitude: ");
            }
        });
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000).build();
    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
}