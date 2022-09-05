package com.example.checkrun.Training;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.checkrun.GpxNode;
import com.example.checkrun.R;
import com.example.checkrun.RecyclerView.CardTraining;
import com.example.checkrun.Utilities;
import com.example.checkrun.ViewModel.TrainingListViewModel;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.io.File;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrainingDetailFragment extends Fragment {

    private TextView nameTraining;
    private TextView dateTraining;
    private MapView mapTraining;
    private TextView descriptionTraining;
    private TextView distanceTraining;
    private TextView timeTraining;
    private TextView averageVelTraining;
    private TextView elevationTraining;
    private TextView equipmentTraining;

    private String userAgent = "MyOwnUserAgent/1.0";
    private float elevationTotal = 0;

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

            //Set user and policy for permission
            Configuration.getInstance().setUserAgentValue(userAgent);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            nameTraining = view.findViewById(R.id.activityNameTraining);
            dateTraining = view.findViewById(R.id.activityDateTraining);
            mapTraining = view.findViewById(R.id.mapView);
            descriptionTraining = view.findViewById(R.id.activityDescriptionTraining);
            distanceTraining = view.findViewById(R.id.activityDistanceTraining);
            timeTraining = view.findViewById(R.id.activityTimeTraining);
            averageVelTraining = view.findViewById(R.id.activityVelAverageTraining);
            elevationTraining = view.findViewById(R.id.activityElevationTraining);
            equipmentTraining = view.findViewById(R.id.activityEquipmentTraining);

            TrainingListViewModel trainingListViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(TrainingListViewModel.class);
            trainingListViewModel.getTrainingSelected().observe(getViewLifecycleOwner(), new Observer<CardTraining>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onChanged(CardTraining cardTraining) {
                    nameTraining.setText(cardTraining.getName());
                    dateTraining.setText(cardTraining.getDate());
                    //Import and set map
                    mapTraining.setMultiTouchControls(true);

                    File gpxFile = new File(cardTraining.getFilePath());
                    List<GpxNode> gpxList = Utilities.decodeGPX(gpxFile);

                    GeoPoint startPoint = new GeoPoint(gpxList.get(0).getLocation().getLatitude(), gpxList.get(0).getLocation().getLongitude());
                    IMapController mapController = mapTraining.getController();
                    mapController.setZoom(16);
                    mapController.setCenter(startPoint);

                    RoadManager roadManager = new OSRMRoadManager(activity, userAgent);
                    ((OSRMRoadManager)roadManager).setMean(OSRMRoadManager.MEAN_BY_FOOT);
                    ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
                    waypoints.add(startPoint);
                    for(int i = 0; i < gpxList.size(); i++) {
                        waypoints.add(new GeoPoint(gpxList.get(i).getLocation().getLatitude(), gpxList.get(i).getLocation().getLongitude()));
                        float elevation = Float.parseFloat(gpxList.get(i).getEle());
                        if(i != 0) {
                            float prevElevation = Float.parseFloat(gpxList.get(i-1).getEle());
                            if(elevation > prevElevation){
                                elevationTotal += (elevation - prevElevation);
                            }
                        }
                    }
                    Road road = roadManager.getRoad(waypoints);

                    Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.RED, 10.0f);
                    mapTraining.getOverlays().add(roadOverlay);
                    mapTraining.invalidate();
                    //Description
                    descriptionTraining.setText(cardTraining.getDescription());
                    //Distance
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setMaximumFractionDigits(2);
                    distanceTraining.setText(decimalFormat.format(cardTraining.getDistance()));
                    //Date
                    long HH = TimeUnit.MILLISECONDS.toHours(cardTraining.getTime());
                    long MM = TimeUnit.MILLISECONDS.toMinutes(cardTraining.getTime()) % 60;
                    long SS = TimeUnit.MILLISECONDS.toSeconds(cardTraining.getTime()) % 60;
                    String finalTime = String.format("%02d:%02d:%02d", HH, MM, SS);
                    timeTraining.setText(finalTime);

                    equipmentTraining.setText(cardTraining.getEquipment());
                    // Extract from db and calculate
                    Duration duration = Duration.ofMillis(cardTraining.getTime());
                    float convTime = duration.toMinutes();
                    float averageVel = cardTraining.getDistance() / (convTime/60);
                    //TODO da cambiare e inserire altitudine
                    averageVelTraining.setText(decimalFormat.format(averageVel));
                    elevationTraining.setText(decimalFormat.format(elevationTotal));
                }
            });
        }
    }
}
