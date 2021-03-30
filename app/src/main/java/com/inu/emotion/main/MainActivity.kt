package com.inu.emotion.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.*
import com.inu.emotion.betting.BettingTemperatureActivity
import com.inu.emotion.emotion.SelectEmotionActivity
import com.inu.emotion.login.LoginActivity
import com.inu.emotion.splash.SplashScreen
import com.inu.emotion.today.ResultActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 스플래시 화면
         * 메인 -> 스플래시 화면(강제) -> 메인
         */
        startActivity(Intent(this, SplashScreen::class.java))

        initMainActivity()


        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        recyclerView.adapter = MenuAdapter(arrayListOf(MenuAdapter.MenuVO("1", 1)))
    }

    private fun initMainActivity() {
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
                R.id.btn_select_emotion     -> Intent(view.context, SelectEmotionActivity::class.java)
                R.id.btn_result             -> Intent(view.context, ResultActivity::class.java)
                R.id.btn_betting_emotion    -> Intent(view.context, BettingTemperatureActivity::class.java)
                R.id.btn_emotion_graph      -> {
                    Toast.makeText(applicationContext, "준비중입니다.", Toast.LENGTH_LONG).show()
                    null
                }
                R.id.image_profile          -> Intent(view.context, LoginActivity::class.java)
                else                        -> throw Exception("BtnClickListener Error : unexpected id")
            }

            if(intent != null) this@MainActivity.startActivity(intent)
        }
    }
}