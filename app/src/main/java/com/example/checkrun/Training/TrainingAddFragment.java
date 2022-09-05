package com.example.checkrun.Training;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
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

import com.example.checkrun.GetFilePathFromDevice;
import com.example.checkrun.GpxNode;
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

import org.osmdroid.util.GeoPoint;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TrainingAddFragment extends Fragment implements OnItemListener{

    private TextView textFilePath;
    private String filePath;
    private Button uploadGpx;

    private float distanceActivityTraining;
    private long timeActivityTraining = 0;

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
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, getString(R.string.choose_file));
                startActivityForResult(chooseFile, 10);

            });

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(date);

            view.findViewById(R.id.button_save_training).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nameActivityTraining.getText() != null && descriptionActivityTraining.getText() != null
                            && equipmentActivityTraining != null && textFilePath != null && distanceActivityTraining != 0
                            && timeActivityTraining != 0) {

                        trainingAddViewModel.addCardTraining(new CardTraining(
                                nameActivityTraining.getText().toString(),
                                descriptionActivityTraining.getText().toString(),
                                textFilePath.getText().toString() ,
                                distanceActivityTraining,
                                timeActivityTraining,
                                formattedDate,
                                equipmentActivityTraining.getText().toString()));

                        ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == Activity.RESULT_OK) {
                    filePath = GetFilePathFromDevice.getPath(getActivity(), data.getData());
                    textFilePath.setText(filePath);

                    File gpxFile = new File(filePath);
                    List<GpxNode> gpxList = Utilities.decodeGPX(gpxFile);
                    float distanceTotal = 0;

                    for(int i = 0; i < gpxList.size(); i++) {
                        if(i != 0){
                            float distancePoint = gpxList.get(i-1).getLocation().distanceTo(gpxList.get(i).getLocation());;
                            distanceTotal += distancePoint;
                        }
                    }
                    distanceActivityTraining = distanceTotal/1000;

                    String timeStart = gpxList.get(0).getTime();
                    String timeEnd = gpxList.get(gpxList.toArray().length - 1).getTime();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    Date date1 = null;
                    Date date2 = null;
                    try {
                        date1 = format.parse(timeStart);
                        date2 = format.parse(timeEnd);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    timeActivityTraining = date2.getTime() - date1.getTime();
                }
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
