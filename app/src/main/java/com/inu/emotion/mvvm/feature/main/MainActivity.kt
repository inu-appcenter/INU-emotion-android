package com.inu.emotion.mvvm.feature.main

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.*
import com.inu.emotion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.main = viewModel

        initMainActivity()

        val list = arrayListOf(
            MenuAdapter.MenuVO("감정선택", R.drawable.img_main_betting),
            MenuAdapter.MenuVO("현황", R.drawable.img_main_chart),
            MenuAdapter.MenuVO("베팅", R.drawable.img_main_betting),
            MenuAdapter.MenuVO("감정기록", R.drawable.img_main_record)
        )

        // recyclerView setting
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerview)
        (recyclerView.layoutManager as GridLayoutManager).apply { spanCount = 2 }
        recyclerView.addItemDecoration(MenuItemDecoration(2, 48, false))
        recyclerView.adapter = MenuAdapter(list)
    }

    private fun initMainActivity() {
        val clickListener = viewModel.BtnClickListener(this)
        findViewById<Button>(R.id.btn_select_emotion).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_result).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_betting_emotion).setOnClickListener(clickListener)
        findViewById<Button>(R.id.btn_emotion_graph).setOnClickListener(clickListener)
        findViewById<ImageView>(R.id.image_profile).setOnClickListener(clickListener)
    }
}