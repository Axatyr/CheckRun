package com.example.checkrun.Training;

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

public class TrainingDetailFragment extends Fragment {

    private TextView nameTraining;
    private TextView dateTraining;
    private TextView distanceTraining;
    private TextView timeTraining;
    private TextView averageVelTraining;
    private TextView maxVelTraining;
    private TextView equipmentTraining;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.training_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null) {
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_training));

            nameTraining = view.findViewById(R.id.activityNameTraining);
            dateTraining = view.findViewById(R.id.activityDateTraining);
            distanceTraining = view.findViewById(R.id.activityDistanceTraining);
            timeTraining = view.findViewById(R.id.activityTimeTraining);
            averageVelTraining = view.findViewById(R.id.activityVelAverageTraining);
            maxVelTraining = view.findViewById(R.id.activityVelMaxTraining);
            equipmentTraining = view.findViewById(R.id.activityEquipmentTraining);

        }
    }
}
