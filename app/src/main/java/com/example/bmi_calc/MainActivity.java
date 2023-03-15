package com.example.bmi_calc;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bmi_calc.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText weightEditText, heightEditText;
    TextView bmiTextView;

    Integer weightAmount = 0;
    Integer heightAmount = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = (EditText) findViewById(R.id.editText_weight);
        heightEditText = (EditText) findViewById(R.id.editText_height);

        bmiTextView = (TextView) findViewById(R.id.txtView_bmi);

        weightEditText.addTextChangedListener(weightEditTextWatcher);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

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