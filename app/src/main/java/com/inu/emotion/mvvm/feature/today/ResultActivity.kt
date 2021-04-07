package com.inu.emotion.mvvm.feature.today

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.inu.emotion.R
import com.inu.emotion.databinding.ActivityResultBinding
import com.inu.emotion.mvvm.feature.common.TemperatureBar
import com.inu.emotion.mvvm.feature.ranking.RankingActivity
import com.inu.emotion.mvvm.model.network.ResultEntity
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        binding.lifecycleOwner = this
        binding.result = viewModel

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener { finish() }

        // SeekBar 터치 불가
        val temperatureBar = findViewById<TemperatureBar>(R.id.thermometer)
        temperatureBar.isEnabled = false

        // 서버로부터 결과 온도 받은 후 출력
        viewModel.request(binding.root)

        // 랭킹 확인 버튼 클릭
        findViewById<Button>(R.id.btn_score_board).setOnClickListener {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }
    }
}