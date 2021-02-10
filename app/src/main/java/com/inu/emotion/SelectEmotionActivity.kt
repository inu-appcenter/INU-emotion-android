package com.inu.emotion

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SelectEmotionActivity : AppCompatActivity() {
    var temperatureBar : TemperatureBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_emotion)

        temperatureBar = findViewById(R.id.thermometer)
        temperatureBar?.setOnSeekBarChangeListener(OnTemperatureBarChangeListener())
    }

    inner class OnTemperatureBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) {

        }

        override fun onStopTrackingTouch(view: SeekBar?) {
        }

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

        }
    }
}