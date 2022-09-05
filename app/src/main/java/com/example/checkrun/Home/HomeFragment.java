package com.example.checkrun.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    TextView usernameTextView;
    TextView distanceTextView;
    TextView timeTextView;
    BarChart chartWeeklyDistance;
    BarChart chartWeeklyTime;

    float totalDistance = 0;
    int totalMinute = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if(activity != null) {
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_home));

            SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
            usernameTextView = view.findViewById(R.id.home_username);
            usernameTextView.setText(sharedPreferences.getString(getString(R.string.text_hintNameUser), "User Name"));

            chartWeeklyDistance = view.findViewById(R.id.chartWeeklyDistance);
            updateWeeklyDistanceChart();

            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);

            distanceTextView = view.findViewById(R.id.distance_run);
            distanceTextView.setText(decimalFormat.format(totalDistance)+ " Km");

            chartWeeklyTime = view.findViewById(R.id.chartWeeklyTime);
            updateWeeklyTimeChart();

            timeTextView = view.findViewById(R.id.time_run);
            timeTextView.setText(totalMinute+ " min");
        }
    }

    private void updateWeeklyDistanceChart(){
        chartWeeklyDistance.setPinchZoom(false);
        chartWeeklyDistance.setDragEnabled(false);
        chartWeeklyDistance.setDrawBarShadow(false);
        chartWeeklyDistance.setDrawGridBackground(false);
        chartWeeklyDistance.getDescription().setEnabled(false);
        chartWeeklyDistance.setDrawValueAboveBar(false);
        chartWeeklyDistance.setTouchEnabled(false);
        chartWeeklyDistance.setHighlightFullBarEnabled(false);

        XAxis xAxis = chartWeeklyDistance.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7);
        xAxis.setCenterAxisLabels(false);
        xAxis.setValueFormatter(new WeekDaysFormatter(chartWeeklyDistance));

        chartWeeklyDistance.getXAxis().setAxisMinimum(0);

        int randomDistance = 30;
        int day = 7;
        ArrayList<BarEntry> values1 = new ArrayList<>();

        for (int i = 0; i < day; i++) {
            float random = ((float)Math.random() * randomDistance);
            values1.add(new BarEntry(i, random ));
            totalDistance += random;
        }

        BarDataSet set1;

        if (chartWeeklyDistance.getData() != null && chartWeeklyDistance.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chartWeeklyDistance.getData().getDataSetByIndex(0);

            set1.setValues(values1);
            chartWeeklyDistance.getData().notifyDataChanged();
            chartWeeklyDistance.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values1, getString(R.string.activity_name));
            set1.setDrawValues(false);
            set1.setColor(Color.rgb(118, 210, 117));

            BarData data = new BarData(set1);

            chartWeeklyDistance.setData(data);
        }

        chartWeeklyDistance.animateY(1000);
        chartWeeklyDistance.invalidate();
    }

    private void updateWeeklyTimeChart(){
        chartWeeklyTime.setPinchZoom(false);
        chartWeeklyTime.setDragEnabled(false);
        chartWeeklyTime.setDrawBarShadow(false);
        chartWeeklyTime.setDrawGridBackground(false);
        chartWeeklyTime.getDescription().setEnabled(false);
        chartWeeklyTime.setDrawValueAboveBar(false);
        chartWeeklyTime.setTouchEnabled(false);
        chartWeeklyTime.setHighlightFullBarEnabled(false);

        XAxis xAxis = chartWeeklyTime.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7);
        xAxis.setCenterAxisLabels(false);
        xAxis.setValueFormatter(new WeekDaysFormatter(chartWeeklyTime));

        chartWeeklyTime.getXAxis().setAxisMinimum(0);

        int randomDistance = 120;
        ArrayList<BarEntry> values1 = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            float random = ((float)Math.random() * randomDistance);
            values1.add(new BarEntry(i, random ));
            totalMinute += random;
        }

        BarDataSet set1;

        if (chartWeeklyTime.getData() != null && chartWeeklyTime.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chartWeeklyTime.getData().getDataSetByIndex(0);

            set1.setValues(values1);
            chartWeeklyTime.getData().notifyDataChanged();
            chartWeeklyTime.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values1, getString(R.string.activity_name));
            set1.setDrawValues(false);
            set1.setColor(Color.rgb(118, 210, 117));

            BarData data = new BarData(set1);

            chartWeeklyTime.setData(data);
        }

        chartWeeklyTime.animateY(1000);
        chartWeeklyTime.invalidate();
    }

    public static class WeekDaysFormatter extends ValueFormatter {
        private final String[] mDays = {" MON ", " TUR ", " WED ", " THU ", " FRI ", " SAT ", " SUN ",};

        private final BarLineChartBase<?> chart;
        private int count;

        public WeekDaysFormatter(BarLineChartBase<?> chart) {
            this.chart = chart;
        }

        @Override
        public String getFormattedValue(float value) {
            //Logger.log(Level.parse("WeekDaysFormatter"), "value = "+value);
            String day;
            try {
                day = mDays[count];
            } catch (IndexOutOfBoundsException ex) {
                count = 0;
                day = mDays[count];
            }

            count++;
            return day;
        }

    }
}
