package com.example.bmi_calc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class BMI_Results : AppCompatActivity() {
    lateinit var graphView: GraphView
    var filename = "bmi.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_results)
        graphView = findViewById(R.id.idGraphView)
        val file = File(filesDir, filename)
        val scanner: Scanner
        scanner = try {
            Scanner(file)
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        }
        val values = ArrayList<Double>()
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val parts = line.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (part in parts) {
                val value = part.trim { it <= ' ' }.toDouble()
                values.add(value)
            }
        }
        val dataPoints = arrayOfNulls<DataPoint>(values.size)
        for (i in values.indices) {
            dataPoints[i] = DataPoint(i.toDouble(), values[i])
        }
        val series = LineGraphSeries(dataPoints)
        graphView.title = getString(R.string.button_BMI_results)
        graphView.titleColor = R.color.purple_700
        graphView.titleTextSize = 45f
        graphView.addSeries(series)
        graphView.viewport.isScrollable = true
        graphView.viewport.isScalable = true
    }
}