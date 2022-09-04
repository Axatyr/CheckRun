package com.example.checkrun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.checkrun.Home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
            Utilities.insertFragment(this, new HomeFragment(), HomeFragment.class.getSimpleName());

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.navigation_home);
        bottomBar.setOnItemSelectedListener (item -> {

            switch (item.getItemId()) {
                case R.id.navigation_training:
                    startActivity(new Intent(MainActivity.this, TrainingActivity.class));
                    onResume();
                    break;
                case R.id.navigation_settings:
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    onResume();
                    break;
            }
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomBar.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);

        menu.findItem(R.id.app_bar_edit_profile).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.app_bar_profile) {
             Intent intent = new Intent(this, ProfileActivity.class);
             this.startActivity(intent);
             return true;
        }
        return false;
    }


}