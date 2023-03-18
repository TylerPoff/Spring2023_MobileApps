package edu.northeastern.numad23sp_tylerpoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_about_me = findViewById(R.id.btnAboutMe);
        Button btn_clicky_clicky = findViewById(R.id.btnClickyClicky);
        Button btn_link_collector = findViewById(R.id.btnLinkCollector);
        Button btn_primes = findViewById(R.id.btnPrimes);
        Button btn_location = findViewById(R.id.btnLocation);

        btn_clicky_clicky.setOnClickListener(v -> openActivityClickyClicky());
        btn_about_me.setOnClickListener(v -> openActivityAboutMe());
        btn_link_collector.setOnClickListener(v -> openActivityLinkCollector());
        btn_primes.setOnClickListener(v -> openActivityPrimes());
        btn_location.setOnClickListener(v -> openActivityLocation());
    }
    public void openActivityAboutMe() {
        Intent intentAboutMe = new Intent(this, AboutMe.class);
        startActivity(intentAboutMe);
    }
    public void openActivityClickyClicky() {
        Intent intentClickyClicky = new Intent(this, ClickyClicky.class);
        startActivity(intentClickyClicky);
    }
    public void openActivityLinkCollector() {
        Intent intentLinkCollector = new Intent(this, LinkCollector.class);
        startActivity(intentLinkCollector);
    }
    public void openActivityPrimes() {
        Intent intentPrimes = new Intent(this, Primes.class);
        startActivity(intentPrimes);
    }
    public void openActivityLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intentLocation = new Intent(this, LocationFinder.class);
            startActivity(intentLocation);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        }
    }
}