package com.example.checkrun;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.checkrun.Home.HomeFragment;

public class Utilities {

    public static void insertFragment(AppCompatActivity activity, Fragment fragment, String tag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container_view with this fragment
        transaction.replace(R.id.fragment_container_view, fragment, tag);
        //add the transaction to the back stack so the user can navigate back except for the HomeFragment
        //TODO Sistemare il back stack per evitare schermata bianca activity
        if (!(fragment instanceof HomeFragment)) {
            transaction.addToBackStack(tag);
        }
        // Commit the transaction
        transaction.commit();
    }

    public static void setUpToolbar(AppCompatActivity activity, String title) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar == null) {
            //create a toolbar that act as SupportActionBar
            Toolbar toolbar = new Toolbar(activity);
            activity.setSupportActionBar(toolbar);
        } else {
            activity.getSupportActionBar().setTitle(title);
        }
    }
}
