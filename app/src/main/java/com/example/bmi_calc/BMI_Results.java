package com.example.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BMI_Results extends AppCompatActivity {

    GraphView graphView;

    String filename = "bmi.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_results);

        graphView = findViewById(R.id.idGraphView);

        File file = new File(getFilesDir(), filename);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Double> values = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\n");
            for (String part : parts) {
                double value = Double.parseDouble(part.trim());
                values.add(value);
            }
        }


        DataPoint[] dataPoints = new DataPoint[values.size()];
        for (int i = 0; i < values.size(); i++) {
            dataPoints[i] = new DataPoint(i, values.get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

        graphView.setTitle(getString(R.string.button_BMI_results));


        graphView.setTitleColor(R.color.purple_700);


        graphView.setTitleTextSize(45);


        graphView.addSeries(series);
        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScalable(true);


    }
}