package com.example.checkrun.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.checkrun.R;
import com.example.checkrun.Utilities;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileEditFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.profile_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_profile));

            TextInputLayout nameInputLayout = view.findViewById(R.id.inputNameUser);
            TextInputLayout ageInputLayout = view.findViewById(R.id.inputAgeUser);
            TextInputLayout weightInputLayout = view.findViewById(R.id.inputWeightUser);
            TextInputLayout heightInputLayout = view.findViewById(R.id.inputHeightUser);

            EditText nameEditText = nameInputLayout.getEditText();
            EditText ageEditText= ageInputLayout.getEditText();
            EditText weightEditText= weightInputLayout.getEditText();
            EditText heightEditText= heightInputLayout.getEditText();

            SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

            Button saveProfile = view.findViewById(R.id.buttonSaveUser);
            saveProfile.setOnClickListener(view1 -> {
                if (nameEditText.getText().toString().length() == 0) {
                    nameEditText.setError(getString(R.string.errorFieldRequired));
                } else if (ageEditText.getText().toString().length() == 0) {
                    ageEditText.setError(getString(R.string.errorFieldRequired));
                } else if (weightEditText.getText().toString().length() == 0) {
                    weightEditText.setError(getString(R.string.errorFieldRequired));
                } else if (heightEditText.getText().toString().length() == 0) {
                    heightEditText.setError(getString(R.string.errorFieldRequired));
                } else if (nameEditText.getText().toString().length() > 20) {
                    nameEditText.setError(getString(R.string.errorLongInput));
                } else if (ageEditText.getText().toString().length() > 3 ) {
                    ageEditText.setError(getString(R.string.errorLongInput));
                } else if (weightEditText.getText().toString().length() > 3) {
                    weightEditText.setError(getString(R.string.errorLongInput));
                } else if (heightEditText.getText().toString().length() > 3) {
                    heightEditText.setError(getString(R.string.errorLongInput));
                } else if (nameEditText.getText().toString().length() != 0 && ageEditText.getText().toString().length() != 0 &&
                        weightEditText.getText().toString().length() != 0 && heightEditText.getText().toString().length() != 0) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.text_hintNameUser), nameEditText.getText().toString());
                    editor.putString(getString(R.string.text_hintAge), ageEditText.getText().toString());
                    String weightComplete = weightEditText.getText().toString() + " " + getString(R.string.suffix_weight);
                    editor.putString(getString(R.string.text_hintWeight), weightComplete);
                    String heightComplete = heightEditText.getText().toString() + " " + getString(R.string.suffix_height);
                    editor.putString(getString(R.string.text_hintHeight), heightComplete);
                    editor.apply();

                    ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_edit_profile).setVisible(false);
        menu.findItem(R.id.app_bar_profile).setVisible(false);
    }
}
