package com.example.checkrun;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.checkrun.Training.TrainingFragment;
import com.example.checkrun.ViewModel.TrainingAddViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrainingActivity extends AppCompatActivity {

    private BottomNavigationView bottomBar;

    private TrainingAddViewModel trainingAddViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        if(savedInstanceState == null)
            Utilities.insertFragment(this, new TrainingFragment(), TrainingFragment.class.getSimpleName());

        //Recover view model to pass data on detail
        //In teoria non serve perche viene usata solamente per la bitmap pero magari a me puo servire per passare il file della mappa o robe del genere
        trainingAddViewModel = new ViewModelProvider(this).get(TrainingAddViewModel.class);

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setSelectedItemId(R.id.navigation_home);
        bottomBar.setOnItemSelectedListener (item -> {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(TrainingActivity.this, MainActivity.class));
                    onResume();
                    break;
                case R.id.navigation_settings:
                    startActivity(new Intent(TrainingActivity.this, SettingsActivity.class));
                    onResume();
                    break;
            }
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomBar.setSelectedItemId(R.id.navigation_training);
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
