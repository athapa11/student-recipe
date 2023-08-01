package com.example.studentrecipes.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentrecipes.R;
import com.example.studentrecipes.data.SQLiteDatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SavedActivity extends AppCompatActivity {

    private static final String TAG = "SavedActivity";
    private SQLiteDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        TextView nameTextView = (TextView) findViewById(R.id.savedTextView);

        // Pass the username for the current session for saving recipes
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String username = bundle.getString("username");
            Log.d(TAG, username);
        }
        else {
            Log.d(TAG, "Bundle is empty");
        }

        db = new SQLiteDatabaseHelper(this);
        db.getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE USERNAME=?", new String[]{username});

        // Bottom Navigation Bar Functionality
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(SavedActivity.this, HomeActivity.class));
                        finish();
                        break;
                    case R.id.nav_guide:
                        startActivity(new Intent(SavedActivity.this, UserGuideActivity.class));
                        finish();
                        break;
                    case R.id.nav_saved:
                        break;
                    case R.id.nav_settings:
                        startActivity(new Intent(SavedActivity.this, SettingsActivity.class));
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