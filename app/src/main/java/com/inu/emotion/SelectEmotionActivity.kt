package com.inu.emotion

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class SelectEmotionActivity : AppCompatActivity() {
    var temperatureBar : TemperatureBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_emotion)

        temperatureBar = findViewById(R.id.thermometer)
        temperatureBar?.setOnSeekBarChangeListener(OnTemperatureBarChangeListener())

        findViewById<Button>(R.id.btn_select_temperature).setOnClickListener {
            val intent = Intent(it.context, SelectElementActivity::class.java)
            startActivity(intent)
        }
    }

    inner class OnTemperatureBarChangeListener : SeekBar.OnSeekBarChangeListener {
        var preId = R.id.left_txt1

        override fun onStartTrackingTouch(p0: SeekBar?) {

        }

        override fun onStopTrackingTouch(view: SeekBar?) {

        }

        override fun onProgressChanged(view: SeekBar?, p1: Int, p2: Boolean) {
            var progress : Int

            if(view == null) Log.d("SelectEmotionActivity : ", "error : view is null")
            else {
                progress = view.progress
                var selectedId = when {
                    (progress <= 10) -> R.id.right_txt5
                    (progress <= 20) -> R.id.left_txt5
                    (progress <= 30) -> R.id.right_txt4
                    (progress <= 40) -> R.id.left_txt4
                    (progress <= 50) -> R.id.right_txt3
                    (progress <= 60) -> R.id.left_txt3
                    (progress <= 70) -> R.id.right_txt2
                    (progress <= 80) -> R.id.left_txt2
                    (progress <= 90) -> R.id.right_txt1
                    (progress <= 100) -> R.id.left_txt1
                    else -> null
                }

                findViewById<TextView>(preId).setTextSize(17F)
                findViewById<TextView>(preId).setTypeface(null, Typeface.NORMAL)
                findViewById<TextView>(selectedId!!).setTextSize(24F)
                findViewById<TextView>(selectedId!!).setTypeface(null, Typeface.BOLD)
                preId = selectedId

                findViewById<TextView>(R.id.tv_temperature).setText(progress.toString() + "ºC")
            }
        }
    }
}