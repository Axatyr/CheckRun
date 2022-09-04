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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardTraining;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.TrainingListViewModel;

import org.w3c.dom.Text;

public class TrainingDetailFragment extends Fragment {

    private TextView nameTraining;
    private TextView dateTraining;
    private TextView descriptionTraining;
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
            //TODO inserire mappa
            descriptionTraining = view.findViewById(R.id.activityDescriptionTraining);
            distanceTraining = view.findViewById(R.id.activityDistanceTraining);
            timeTraining = view.findViewById(R.id.activityTimeTraining);
            averageVelTraining = view.findViewById(R.id.activityVelAverageTraining);
            maxVelTraining = view.findViewById(R.id.activityVelMaxTraining);
            equipmentTraining = view.findViewById(R.id.activityEquipmentTraining);

            TrainingListViewModel trainingListViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(TrainingListViewModel.class);
            trainingListViewModel.getTrainingSelected().observe(getViewLifecycleOwner(), new Observer<CardTraining>() {
                @Override
                public void onChanged(CardTraining cardTraining) {
                    nameTraining.setText(cardTraining.getName());
                    dateTraining.setText(cardTraining.getDate());
                    descriptionTraining.setText(cardTraining.getDescription());
                    distanceTraining.setText(String.valueOf(cardTraining.getDistance()));
                    timeTraining.setText(String.valueOf(cardTraining.getTime()));
                    equipmentTraining.setText(cardTraining.getEquipment());
                    // Extract from db and calculate
                    float convTime = ((float) cardTraining.getTime())/60;
                    float averageVel = cardTraining.getDistance() / convTime;
                    //TODO da vedere come calcolarla
                    float maxVel = averageVel;
                    averageVelTraining.setText(String.valueOf(averageVel));
                    maxVelTraining.setText(String.valueOf(maxVel));
                }
            });
        }
    }
}
