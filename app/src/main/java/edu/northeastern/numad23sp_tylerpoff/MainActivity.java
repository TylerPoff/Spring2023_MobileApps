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
        Button btn_link_collector = findViewById(R.id.btnLinkCollector);
        btn_clicky_clicky.setOnClickListener(v -> openActivityClickyClicky());
        btn_about_me.setOnClickListener(v -> openActivityAboutMe());
        btn_link_collector.setOnClickListener(v -> openActivityLinkCollector());

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
}