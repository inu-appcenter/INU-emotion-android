package com.inu.emotion.mvvm.feature.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.*
import com.inu.emotion.databinding.ActivityMainBinding
import com.inu.emotion.mvvm.feature.betting.BettingTemperatureActivity
import com.inu.emotion.mvvm.feature.emotion.SelectEmotionActivity
import com.inu.emotion.mvvm.feature.login.LoginActivity
import com.inu.emotion.mvvm.feature.splash.SplashActivity
import com.inu.emotion.mvvm.feature.today.ResultActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.main = viewModel

        initMainActivity()

        // TODO : 버튼 -> 리사이클러뷰
//        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
//        recyclerView.adapter = MenuAdapter(arrayListOf(MenuAdapter.MenuVO("123123123123123", 1)))
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