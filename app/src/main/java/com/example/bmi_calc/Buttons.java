package com.example.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Buttons extends AppCompatActivity {

    private Button bmi_move;
    private Button cls_move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        bmi_move = findViewById(R.id.button_BMI_Calculator);
        cls_move = findViewById(R.id.button_Cls_Calculator);
        bmi_move.setOnClickListener(view -> {
            Intent intent = new Intent(Buttons.this, BMI_Calculator.class);
            startActivity(intent);
        });
        cls_move.setOnClickListener(view -> {
            Intent intent = new Intent(Buttons.this, Cals_Calculator.class);
            startActivity(intent);
        });
    }
}