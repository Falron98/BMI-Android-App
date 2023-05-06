package com.example.bmi_calc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class BMI_Calculator : AppCompatActivity() {
    var weightEditText: EditText? = null
    var heightEditText: EditText? = null
    var bmiTextView: TextView? = null
    lateinit var result_save: Button
    var weightAmount = 0
    var heightAmount = 0
    var bmi: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)
        weightEditText = findViewById<View>(R.id.editText_weight) as EditText
        heightEditText = findViewById<View>(R.id.editText_height) as EditText
        bmiTextView = findViewById<View>(R.id.txtView_bmi) as TextView
        result_save = findViewById(R.id.button_save)
        weightEditText!!.addTextChangedListener(weightEditTextWatcher)
        heightEditText!!.addTextChangedListener(heightEditTextWatcher)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        result_save.setOnClickListener(View.OnClickListener { v: View? ->
            if (bmi != null) {
                val df = DecimalFormat("##.##")
                val roundedValue = df.format(bmi).toDouble()
                val filename = "bmi.txt"
                val fileContents = """
                $roundedValue
                
                """.trimIndent()
                val file = File(filesDir, filename)
                if (!file.exists()) {
                    try {
                        file.createNewFile()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                try {
                    val writer = FileWriter(file, true)
                    writer.append(fileContents)
                    writer.flush()
                    writer.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun calculate() {
        bmi = weightAmount.toDouble() / ((heightAmount * heightAmount).toDouble() / 10000)
        bmiTextView!!.text = NumberFormat.getNumberInstance().format(bmi)
    }

    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            weightAmount = try {
                charSequence.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (weightAmount > 0 && heightAmount > 0) {
                calculate()
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            heightAmount = try {
                charSequence.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (weightAmount > 0 && heightAmount > 0) {
                calculate()
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }
}