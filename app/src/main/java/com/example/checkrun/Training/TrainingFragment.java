package com.example.checkrun.Training;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardTraining;
import com.example.checkrun.RecyclerView.CardTrainingAdapter;
import com.example.checkrun.RecyclerView.OnItemListener;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.TrainingAddViewModel;
import com.example.checkrun.ViewModel.TrainingListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment implements OnItemListener{

    private static final String LOG_TAG = "Home-Fragment";

    private CardTrainingAdapter adapter;
    private RecyclerView recyclerView;
    private TrainingListViewModel trainingListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.training, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null) {
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_training));

            setRecycleView(activity);

            trainingListViewModel = new ViewModelProvider(activity).get(TrainingListViewModel.class);
            trainingListViewModel.getCardTrainings().observe(activity, new Observer<List<CardTraining>>() {
                @Override
                public void onChanged(List<CardTraining> cardTrainings) {
                    adapter.setData(cardTrainings);
                }
            });

            Button addTraining = view.findViewById(R.id.button_add_training);
            addTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new TrainingAddFragment(), TrainingAddFragment.class.getSimpleName());
                }
            });

        } else {
            Log.e(LOG_TAG, "Activity is null");
        }
    }

    private void setRecycleView(final Activity activity) {
        recyclerView = activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final OnItemListener listener = this;
        adapter = new CardTrainingAdapter(listener, activity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Activity activity = getActivity();
        if(activity != null){
            Utilities.insertFragment((AppCompatActivity) activity, new TrainingDetailFragment(), TrainingDetailFragment.class.getSimpleName());

            trainingListViewModel.setTrainingSelected(adapter.getItemSelected(position));
        }
    }
}
