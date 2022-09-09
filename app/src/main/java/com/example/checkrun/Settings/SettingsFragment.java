package com.example.checkrun.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.checkrun.R;
import com.example.checkrun.Utilities;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null) {
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_settings));

            Button equipmentButton = view.findViewById(R.id.button_equipment);
            equipmentButton.setOnClickListener(view1 -> Utilities.insertFragment((AppCompatActivity) activity, new EquipmentFragment(), EquipmentFragment.class.getSimpleName()));

            Button infoButton = view.findViewById(R.id.button_info);
            infoButton.setOnClickListener(view12 -> Utilities.insertFragment((AppCompatActivity) activity, new InfoFragment(), InfoFragment.class.getSimpleName()));
        }
    }
}
