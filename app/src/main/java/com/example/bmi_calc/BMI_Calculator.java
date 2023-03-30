package com.example.bmi_calc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Objects;

public class BMI_Calculator extends AppCompatActivity {
    EditText weightEditText, heightEditText;
    TextView bmiTextView;

    Integer weightAmount = 0;
    Integer heightAmount = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weightEditText = (EditText) findViewById(R.id.editText_weight);
        heightEditText = (EditText) findViewById(R.id.editText_height);

        bmiTextView = (TextView) findViewById(R.id.txtView_bmi);

        weightEditText.addTextChangedListener(weightEditTextWatcher);
        heightEditText.addTextChangedListener(heightEditTextWatcher);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    private void calculate(){
        Double bmi = (double) weightAmount / (double) ((double)(heightAmount*heightAmount)/10000);

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