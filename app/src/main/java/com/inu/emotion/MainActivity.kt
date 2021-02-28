package com.inu.emotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickListener = BtnClickListener()
        findViewById<Button>(R.id.btn_select_emotion).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_result).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_betting_emotion).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_emotion_graph).setOnClickListener(clickListener)
        findViewById<ImageView>(R.id.image_profile).setOnClickListener(clickListener)
    }

    inner class BtnClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            val intent = when(view?.id) {
                R.id.btn_select_emotion -> Intent(view.context, SelectEmotionActivity::class.java)
                R.id.btn_result -> Intent(view.context, ResultActivity::class.java)
                R.id.btn_betting_emotion -> Intent(view.context, BettingTemperatureActivity::class.java)
                R.id.btn_emotion_graph -> {
                    Toast.makeText(applicationContext, "준비중입니다.", Toast.LENGTH_LONG).show()
                    Intent(view.context, SelectTemperatureActivity::class.java)
                }
                R.id.image_profile -> Intent(view.context, LoginActivity::class.java)

                else -> throw Exception("BtnClickListener Error : unexpected id")
            }

            if(intent != null) this@MainActivity.startActivity(intent)
        }
    }
}