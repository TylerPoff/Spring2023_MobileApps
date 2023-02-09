package edu.northeastern.numad23sp_tylerpoff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.concurrent.locks.ReentrantLock;

public class Primes extends AppCompatActivity {
    private static final String STATE_PRIMES = "primes";
    private Button btnStartPrimes;
    private Button btnEndPrimes;
    private TextView lastPrimeTV;
    private TextView checkingPrimeTV;
    private CheckBox primeSwitch;
    private Handler textHandler = new Handler();
    private boolean terminate;
    private boolean isSearching;
    private int checking;
    private int lastPrime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primes);
        if (savedInstanceState == null) {
            terminate = false;
            isSearching = false;
            checking = 3;
            lastPrime = 0;
        }
        cancelSearch();
        startSearch();
        primeSwitch = findViewById(R.id.primeSwitch);
        lastPrimeTV = findViewById(R.id.lastPrimeTV);
        checkingPrimeTV = findViewById(R.id.checkingPrimeTV);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("checking_tv", checkingPrimeTV.getText().toString());
        outState.putString("last_prime_tv", lastPrimeTV.getText().toString());
        outState.putBoolean("is_searching", isSearching);
        outState.putBoolean("terminate", terminate);
        outState.putInt("checking", checking);
        outState.putInt("last_prime", lastPrime);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        checkingPrimeTV.setText(savedInstanceState.getString("checking_tv"));
        lastPrimeTV.setText(savedInstanceState.getString("last_prime_tv"));
        isSearching = savedInstanceState.getBoolean("is_searching");
        checking = savedInstanceState.getInt("checking");
        lastPrime = savedInstanceState.getInt("last_prime");
        terminate = savedInstanceState.getBoolean("terminate");
        if(isSearching) {
            runOnRunnableThread();
        }
    }

    @Override
    public void onBackPressed() {
        if(isSearching) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Search in progress. Are you sure you want to leave?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            super.onBackPressed();
        }
    }

    public void createDialogTerminate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to terminate?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                terminate = true;
                checking = 3;
                lastPrime = 0;
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void cancelSearch() {
        btnEndPrimes = findViewById(R.id.btnEndPrimes);
        btnEndPrimes.setOnClickListener(v -> createDialogTerminate());
    }

    public void startSearch() {
        btnStartPrimes = findViewById(R.id.btnStartPrimes);
        btnStartPrimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSearching) {
                    runOnRunnableThread();
                }
            }
        });
    }
    public boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void runOnRunnableThread() {
        isSearching = true;
        primeSearch primeSearch = new primeSearch();
        new Thread(primeSearch).start();
    }

    class primeSearch implements Runnable {
        @Override
        public void run() {
            for (int i = checking; i <= 2147483647; i = i + 2) {
                if(terminate) {
                    break;
                }
                if (isPrime(i)) {
                    lastPrime = i;
                }
                checking = i;
                final int iFinal = i;
                final int primeFinal = lastPrime;
                textHandler.post(() -> {
                    checkingPrimeTV.setText("Checking: " + iFinal);
                    lastPrimeTV.setText("Last Prime Found: " + primeFinal);
                });
                try {
                    Thread.sleep(200);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            terminate = false;
            isSearching = false;
        }
    }

}