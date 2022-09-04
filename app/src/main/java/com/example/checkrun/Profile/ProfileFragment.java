package com.example.checkrun.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.checkrun.R;
import com.example.checkrun.Utilities;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_profile));

            TextView nameTextView = view.findViewById(R.id.userTextView);
            TextView ageTextView = view.findViewById(R.id.ageTextView);
            TextView weightTextView = view.findViewById(R.id.weightTextView);
            TextView heightTextView = view.findViewById(R.id.heightTextView);

            SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

            nameTextView.setText(sharedPreferences.getString(getString(R.string.text_hintNameUser), "User Name"));
            ageTextView.setText(sharedPreferences.getString(getString(R.string.text_hintAge), "-"));
            weightTextView.setText(sharedPreferences.getString(getString(R.string.text_hintWeight), "-"));
            heightTextView.setText(sharedPreferences.getString(getString(R.string.text_hintHeight), "-"));
        }
    }
}
