package com.example.checkrun;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.checkrun.Profile.ProfileEditFragment;
import com.example.checkrun.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private BottomNavigationView bottomBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(savedInstanceState == null)
            Utilities.insertFragment(this, new ProfileFragment(), ProfileFragment.class.getSimpleName());

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.navigation_home);
        bottomBar.setOnItemSelectedListener (item -> {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    onResume();
                    break;
                case R.id.navigation_training:
                    startActivity(new Intent(ProfileActivity.this, TrainingActivity.class));
                    onResume();
                    break;
                case R.id.navigation_settings:
                    startActivity(new Intent(ProfileActivity.this, SettingsActivity.class));
                    onResume();
                    break;
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);

        menu.findItem(R.id.app_bar_profile).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.app_bar_edit_profile) {
            Utilities.insertFragment(this, new ProfileEditFragment(), ProfileEditFragment.class.getSimpleName());
            return true;
        }
        return false;
    }
}
