package com.inu.emotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast

class SelectEmotionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_emotion)

        findViewById<com.inu.emotion.TemperatureBar>(R.id.thermometer).setOnSeekBarChangeListener(OnTemperatureBarChangeListener())
    }

    inner class OnTemperatureBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) {

        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            Toast.makeText(this@SelectEmotionActivity, "I am Groot!", Toast.LENGTH_LONG).show()
        }

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

        }
    }
}