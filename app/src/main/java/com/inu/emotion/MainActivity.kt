package com.inu.emotion

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickListener = BtnClickListener()
        findViewById<Button>(R.id.btn_select_emotion).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_betting_emotion).setOnClickListener(clickListener)
    }

    inner class BtnClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            val intent = when(view!!.id) {
                // TODO : 홈 화면에서 버튼 클릭 -> 화면 전환에 연결되는 액티비티를 지정
                R.id.btn_select_emotion -> Intent(view.context, SelectEmotionActivity::class.java)
                R.id.btn_betting_emotion -> Intent(view.context, SelectEmotionActivity::class.java)
                else -> throw Exception("BtnClickListener Error : unexpected id")
            }
            this@MainActivity.startActivity(intent)
        }
    }
}