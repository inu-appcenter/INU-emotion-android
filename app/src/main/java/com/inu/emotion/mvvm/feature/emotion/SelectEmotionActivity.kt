package com.inu.emotion.mvvm.feature.emotion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.inu.emotion.R
import com.inu.emotion.databinding.ActivityEmotionBinding
import com.inu.emotion.mvvm.feature.common.TemperatureBar
import com.inu.emotion.mvvm.feature.elements.SelectElementActivity

class SelectEmotionActivity : AppCompatActivity() {
    var temperatureBar: TemperatureBar? = null
    private lateinit var binding: ActivityEmotionBinding
    private val viewModel: EmotionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_emotion)
        binding.lifecycleOwner = this
        binding.emotion = viewModel

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener { finish() }

        // 온도계 활성화
        temperatureBar = findViewById(R.id.thermometer)
        temperatureBar?.setOnSeekBarChangeListener(viewModel.OnTemperatureBarChangeListener(binding.root))

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
}