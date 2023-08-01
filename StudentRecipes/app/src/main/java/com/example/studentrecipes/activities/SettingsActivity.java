package com.example.studentrecipes.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.studentrecipes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    private final static String TAG = "SettingsActivity";
    SwitchCompat switchTheme;
    SharedPreferences settingsPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Setting light/night mode functionality
        switchTheme = (SwitchCompat) findViewById(R.id.switch_theme);

        settingsPreferences = getSharedPreferences("themePreference", 0);
        Boolean isNightModeOn = settingsPreferences.getBoolean("night_mode", false);
        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchTheme.setChecked(true);
        }

        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchTheme.setChecked(true);
                    SharedPreferences.Editor editor = settingsPreferences.edit();
                    editor.putBoolean("night_mode", true);
                    editor.commit();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchTheme.setChecked(false);
                    SharedPreferences.Editor editor = settingsPreferences.edit();
                    editor.putBoolean("night_mode", false);
                    editor.commit();
                }
            }
        });

        // Bottom Navigation Bar Functionality
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                        finish();
                        break;
                    case R.id.nav_guide:
                        startActivity(new Intent(SettingsActivity.this, UserGuideActivity.class));
                        finish();
                        break;
                    case R.id.nav_saved:
                        startActivity(new Intent(SettingsActivity.this, SavedActivity.class));
                        finish();
                        break;
                    case R.id.nav_settings:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"onPause");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Log.v(TAG,"onBackPressed");
    }
}
