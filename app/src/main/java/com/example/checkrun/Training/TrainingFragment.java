package com.example.checkrun.Training;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardTrainingAdapter;
import com.example.checkrun.RecyclerView.OnItemListener;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.TrainingListViewModel;


public class TrainingFragment extends Fragment implements OnItemListener{

    private static final String LOG_TAG = "Training - Fragment";
    private CardTrainingAdapter adapter;
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

            trainingListViewModel.getCardTrainings().observe(activity, cardTrainings -> adapter.setData(cardTrainings));

            Button addTraining = view.findViewById(R.id.button_add_training);
            addTraining.setOnClickListener(view1 -> Utilities.insertFragment((AppCompatActivity) activity, new TrainingAddFragment(), TrainingAddFragment.class.getSimpleName()));

        } else {
            Log.e(LOG_TAG, "Activity is null");
        }
    }

    private void setRecycleView(final Activity activity) {
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final OnItemListener listener = this;
        adapter = new CardTrainingAdapter(listener, activity);
        trainingListViewModel = new ViewModelProvider(requireActivity()).get(TrainingListViewModel.class);
        trainingListViewModel.getCardTrainings().observe(requireActivity(), cardTrainings -> adapter.notifyDataSetChanged());
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
