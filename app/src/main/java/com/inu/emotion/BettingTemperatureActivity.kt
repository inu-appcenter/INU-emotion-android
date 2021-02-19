package com.inu.emotion

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView

class BettingTemperatureActivity : AppCompatActivity() {
    var temperatureBar : TemperatureBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_betting_temperature)

        temperatureBar = findViewById(R.id.thermometer)
        temperatureBar?.setOnSeekBarChangeListener(OnTemperatureBarChangeListener())
    }

    // 온도계 터치
    inner class OnTemperatureBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(view: SeekBar?) {}
        override fun onProgressChanged(view: SeekBar?, p1: Int, p2: Boolean) {
            var progress: Int

            if (view == null) Log.d("BettingActivity : ", "error : view is null")
            else {
                progress = view.progress
                findViewById<TextView>(R.id.tv_temperature).setText(progress.toString() + "ºC")
            }
        }
    }
}