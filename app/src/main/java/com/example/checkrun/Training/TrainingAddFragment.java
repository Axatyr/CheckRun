package com.example.checkrun.Training;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardEquipment;
import com.example.checkrun.RecyclerView.CardEquipmentAdapter;
import com.example.checkrun.RecyclerView.CardTraining;
import com.example.checkrun.RecyclerView.CardTrainingAdapter;
import com.example.checkrun.RecyclerView.OnItemListener;
import com.example.checkrun.TrainingActivity;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.EquipmentListViewModel;
import com.example.checkrun.ViewModel.TrainingAddViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TrainingAddFragment extends Fragment implements OnItemListener{

    private TextView textFilePath;

    private Uri fileUri;
    private String filePath;
    private File file;
    private Button uploadGpx;

    private TextInputEditText nameActivityTraining;
    private TextInputEditText descriptionActivityTraining;
    private AutoCompleteTextView equipmentActivityTraining;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.training_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_addTraining));

            TrainingAddViewModel trainingAddViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(TrainingAddViewModel.class);

            nameActivityTraining = view.findViewById(R.id.inputName);
            descriptionActivityTraining = view.findViewById(R.id.inputDescription);
            equipmentActivityTraining = view.findViewById(R.id.inputEquipment);

            //TODO Aggiungere le scarpe da db in futuro
            String[] equipmentItems = new String[] {"Nike Pegasus 37", "Salomon SpeedCross 5", "Asics gelPulse 8"};
            ArrayAdapter<String> adapterEquipment = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, equipmentItems);
            equipmentActivityTraining.setAdapter(adapterEquipment);

            uploadGpx = view.findViewById(R.id.button_upload_gpx);
            textFilePath = view.findViewById(R.id.file_path);

            uploadGpx.setOnClickListener(view1 -> {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/gpx+xml");
                chooseFile = Intent.createChooser(chooseFile, getString(R.string.choose_file));
                someActivityResultLauncher.launch(chooseFile);
            });

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(date);

            view.findViewById(R.id.button_save_training).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nameActivityTraining.getText() != null && descriptionActivityTraining.getText() != null && equipmentActivityTraining != null && textFilePath != null) {

                        trainingAddViewModel.addCardTraining(new CardTraining(
                                nameActivityTraining.getText().toString(),
                                descriptionActivityTraining.getText().toString(),
                                textFilePath.getText().toString() ,
                                0,
                                0,
                                formattedDate,
                                equipmentActivityTraining.getText().toString()));

                        ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                    }
                }
            });
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Here, no request code
                        Intent data = result.getData();
                        fileUri = data.getData();
                        filePath = fileUri.getPath();
                        textFilePath.setText(filePath);
                        file = new File(fileUri.getPath());
                    }
                }
            });

    @Override
    public void onItemClick(int position) {

    }
}
