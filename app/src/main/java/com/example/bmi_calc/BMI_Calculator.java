package com.example.bmi_calc;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Objects;

public class BMI_Calculator extends AppCompatActivity {
    EditText weightEditText, heightEditText;
    TextView bmiTextView;

    Button result_save;

    Integer weightAmount = 0;
    Integer heightAmount = 0;
    Double bmi;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightEditText = (EditText) findViewById(R.id.editText_weight);
        heightEditText = (EditText) findViewById(R.id.editText_height);

        bmiTextView = (TextView) findViewById(R.id.txtView_bmi);

        result_save = findViewById(R.id.button_save);

        weightEditText.addTextChangedListener(weightEditTextWatcher);
        heightEditText.addTextChangedListener(heightEditTextWatcher);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        result_save.setOnClickListener(v -> {
            if(bmi != null){
                DecimalFormat df = new DecimalFormat("##.##");
                double roundedValue = Double.parseDouble(df.format(bmi));

                String filename = "bmi.txt";
                String fileContents = roundedValue +"\n";

                File file = new File(getFilesDir(), filename);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    FileWriter writer = new FileWriter(file, true);
                    writer.append(fileContents);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void calculate(){
        bmi = (double) weightAmount / (double) ((double)(heightAmount*heightAmount)/10000);

        bmiTextView.setText(NumberFormat.getNumberInstance().format(bmi));
    }

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                weightAmount = Integer.parseInt(charSequence.toString());

            }
            catch (NumberFormatException e){
                weightAmount = 0;
            }
            if(weightAmount > 0 && heightAmount > 0){
                calculate();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                heightAmount = Integer.parseInt(charSequence.toString());

            }
            catch (NumberFormatException e){
                heightAmount = 0;
            }
            if(weightAmount > 0 && heightAmount > 0){
                calculate();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}