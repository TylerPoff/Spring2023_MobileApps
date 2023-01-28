package edu.northeastern.numad23sp_tylerpoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_about_me = findViewById(R.id.btnAboutMe);
        Button btn_clicky_clicky = findViewById(R.id.btnClickyClicky);
        btn_about_me.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Name: Tyler Poff\nEmail: poff.t@northeastern.edu"
        , Toast.LENGTH_LONG).show());
        btn_clicky_clicky.setOnClickListener(v -> openActivityClickyClicky());
    }
    public void openActivityClickyClicky() {
        Intent intentClickyClicky = new Intent(this, ClickyClicky.class);
        startActivity(intentClickyClicky);
    }
}