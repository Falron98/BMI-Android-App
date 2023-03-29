package com.example.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buttons extends AppCompatActivity {

    private Button bmi_move;
    private Button cls_move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        bmi_move = findViewById(R.id.button_BMI_Calculator);
        bmi_move.setOnClickListener(view -> {
            Intent intent = new Intent(Buttons.this, MainActivity.class);
            startActivity(intent);
        });
    }
}