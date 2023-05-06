package com.example.bmi_calc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Buttons : AppCompatActivity() {
    private lateinit var bmi_move: Button
    private lateinit var bmi_results_move: Button
    private lateinit var cls_move: Button
    private lateinit var rcp_move: Button
    private lateinit var quiz_move: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)
        bmi_move = findViewById(R.id.button_BMI_Calculator)
        bmi_results_move = findViewById(R.id.button_bmi_results)
        cls_move = findViewById(R.id.button_Cls_Calculator)
        rcp_move = findViewById(R.id.button_Recipes)
        quiz_move = findViewById(R.id.button_Quiz)
        bmi_move.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@Buttons, BMI_Calculator::class.java)
            startActivity(intent)
        })
        bmi_results_move.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@Buttons, BMI_Results::class.java)
            startActivity(intent)
        })
        cls_move.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@Buttons, Cals_Calculator::class.java)
            startActivity(intent)
        })
        rcp_move.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@Buttons, Recipes::class.java)
            startActivity(intent)
        })
        quiz_move.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@Buttons, Quiz::class.java)
            startActivity(intent)
        })
    }
}