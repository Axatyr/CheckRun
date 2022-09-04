package com.example.checkrun.Settings;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardEquipment;
import com.example.checkrun.RecyclerView.CardEquipmentAdapter;
import com.example.checkrun.RecyclerView.CardTraining;
import com.example.checkrun.RecyclerView.CardTrainingAdapter;
import com.example.checkrun.RecyclerView.OnItemListener;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.EquipmentListViewModel;
import com.example.checkrun.ViewModel.TrainingListViewModel;

import java.util.List;

public class EquipmentFragment extends Fragment implements OnItemListener{

    private static final String LOG_TAG = "Equipment - Fragment";

    private CardEquipmentAdapter adapter;
    private RecyclerView recyclerView;
    private EquipmentListViewModel equipmentListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.equipment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null) {
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_equipment));

            setRecycleView(activity);

            equipmentListViewModel = new ViewModelProvider(activity).get(EquipmentListViewModel.class);
            equipmentListViewModel.getCardEquipments().observe(activity, new Observer<List<CardEquipment>>() {
                @Override
                public void onChanged(List<CardEquipment> cardEquipments) {
                    adapter.setData(cardEquipments);
                }
            });

            Button addEquipment = view.findViewById(R.id.button_add_equipment);
            addEquipment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new EquipmentAddFragment(), EquipmentAddFragment.class.getSimpleName());
                }
            });
        } else {
            Log.e(LOG_TAG, "Activity is null");
        }
    }

    private void setRecycleView(final Activity activity) {
        recyclerView = activity.findViewById(R.id.recycler_view_equipment);
        recyclerView.setHasFixedSize(true);
        final OnItemListener listener = this;
        adapter = new CardEquipmentAdapter(listener, activity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {

    }
}
