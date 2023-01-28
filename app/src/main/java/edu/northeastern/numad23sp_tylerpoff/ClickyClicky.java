package edu.northeastern.numad23sp_tylerpoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClicky extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        TextView youPressed = findViewById(R.id.youPressed);
        int btnID = v.getId();
        if(btnID == R.id.btnA) {
            youPressed.setText("You Pressed: A");
        }
        else if(btnID == R.id.btnB) {
            youPressed.setText("You Pressed: B");
        }
        else if(btnID == R.id.btnC) {
            youPressed.setText("You Pressed: C");
        }
        else if(btnID == R.id.btnD) {
            youPressed.setText("You Pressed: D");
        }
        else if(btnID == R.id.btnE) {
            youPressed.setText("You Pressed: E");
        }
        else if(btnID == R.id.btnF) {
            youPressed.setText("You Pressed: F");
        }
    }
}