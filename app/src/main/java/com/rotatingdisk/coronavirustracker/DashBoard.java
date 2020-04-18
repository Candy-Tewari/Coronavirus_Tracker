package com.rotatingdisk.coronavirustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity {

    private static final String SHARED_PREF = "Coronavirus Tracker";
    private static final String ACTIVE_STATUS = "active cases";
    private static final String DISCHARGED = "discharged cases";
    private static final String DEATHS = "deaths";
    private static final String MIGRATED = "migrated";
    private static final String STATE_DATA = "state data";
    private static final String LAST_DATE = "last date";
    private static final String LAST_UNCHANGED_ACTIVE_CASES = "last unchanged";
    private static final String LAST_UNCHANGED_DISCHARGED_CASES = "discharged unchanged";
    private static final String LAST_UNCHANGED_DEATHS_CASES = "death unchanged";
    private static final String LAST_UNCHANGED_MIGRATED_CASES = "migrated unchanged";

    private void getBottomNavigationViewWorking() {

        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.health:
                        selectedFragment = new HealthFragment();
                        break;
                    case R.id.global:
                        selectedFragment = new GlobalFragment();
                        break;
                    default:
                        selectedFragment = new HomeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });
    }

    private void getNewData() {
        FetchData fetchData = new FetchData();
        if (fetchData.collectData(this)) {//This is asking we are able to get the new data. If yes then lets update the data.
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            Toast.makeText(this, "Internet not available", Toast.LENGTH_SHORT).show();
        }
    }

    public void openWhatsapp(View view) {
        try {
            String number = "9013151515";
            String text = "Namaste";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + number + "&text=" + text));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Whatsapp service unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void openCalling(View view) {
        String num = "01123978046";
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
        }
        else
        { startActivity(intent); }
    }
    public void openGmail(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","ncov2019@gov.in", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, ""));
    }
    public void knowMore(View view){
        String id = "BtN-goy9VOY";
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        getBottomNavigationViewWorking();
        getNewData();
    }
}