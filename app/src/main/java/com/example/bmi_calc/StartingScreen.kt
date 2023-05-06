package com.example.bmi_calc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class StartingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting_screen)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(this@StartingScreen, Buttons::class.java))
                finish()
            }
        }, 3000)
    }
}