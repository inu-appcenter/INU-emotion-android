package com.inu.emotion

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.inu.emotion.customView.TemperatureBar

class SelectEmotionActivity : AppCompatActivity() {
    var temperatureBar : TemperatureBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_emotion)

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener{finish()}

        temperatureBar = findViewById(R.id.thermometer)
        temperatureBar?.setOnSeekBarChangeListener(OnTemperatureBarChangeListener())

        // 확인 버튼 클릭
        findViewById<Button>(R.id.btn_select_temperature).setOnClickListener {
            val intent = Intent(it.context, SelectElementActivity::class.java)
            intent.putExtra("temperature", temperatureBar!!.progress)
            this.startActivityForResult(intent, 10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 10 && resultCode == Activity.RESULT_OK) finish()
    }

    // 온도계 터치
    inner class OnTemperatureBarChangeListener : SeekBar.OnSeekBarChangeListener {
        var preId = R.id.right_txt3

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