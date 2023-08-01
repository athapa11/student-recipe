package com.example.studentrecipes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import com.example.studentrecipes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserGuideActivity extends AppCompatActivity {

    private final static String TAG = "UserGuideActivity";
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userguide);

        wv = (WebView) findViewById(R.id.webview);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/guide.html");

        // Bottom Navigation Bar Functionality
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(UserGuideActivity.this, HomeActivity.class));
                        finish();
                        break;
                    case R.id.nav_guide:
                        break;
                    case R.id.nav_saved:
                        startActivity(new Intent(UserGuideActivity.this, SavedActivity.class));
                        finish();
                        break;
                    case R.id.nav_settings:
                        startActivity(new Intent(UserGuideActivity.this, SettingsActivity.class));
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_action_provider,menu);

        // Share Action Provider reference
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        // Reference to WebView
        wv = (WebView) findViewById(R.id.webview);
        intent.putExtra(Intent.EXTRA_TEXT, wv.getUrl());

        myShareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
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
