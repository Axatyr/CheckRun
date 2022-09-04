package com.example.checkrun.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardEquipment;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.EquipmentAddViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EquipmentAddFragment extends Fragment {

    TextInputEditText nameEquipmentTextInput;
    TextInputEditText distanceMaxEquipmentTextInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.equipment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null) {
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_equipment));

            EquipmentAddViewModel equipmentAddViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(EquipmentAddViewModel.class);

            nameEquipmentTextInput = view.findViewById(R.id.inputNameEquipment);
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(date);
            distanceMaxEquipmentTextInput = view.findViewById(R.id.inputDistanceEquipment);
            float distanceCurrent = 0;
            view.findViewById(R.id.button_save_equipment).setOnClickListener(view1 -> {
                try {
                    if (nameEquipmentTextInput.getText() != null && distanceMaxEquipmentTextInput.getText() != null) {
                        equipmentAddViewModel.addCardEquipment(new CardEquipment(nameEquipmentTextInput.getText().toString(),
                                Float.parseFloat(distanceMaxEquipmentTextInput.getText().toString()),
                                distanceCurrent,
                                formattedDate));

                        ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
