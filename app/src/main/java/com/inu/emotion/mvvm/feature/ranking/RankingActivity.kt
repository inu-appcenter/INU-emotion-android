package com.inu.emotion.mvvm.feature.ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        // 더미 데이터
        val rankingList = getDummyData()
        val recyclerView = findViewById<RecyclerView>(R.id.ranking_recyclerview)
        recyclerView.adapter = RankingAdapter(rankingList)
        findViewById<ImageView>(R.id.undo).setOnClickListener { finish() }
    }

    fun getDummyData() = arrayListOf(
        RankingAdapter.RankingInfo(1, "더미"),
        RankingAdapter.RankingInfo(2, "데이터"),
        RankingAdapter.RankingInfo(3, "입니다"),
        RankingAdapter.RankingInfo(4, "만"),
        RankingAdapter.RankingInfo(5, "무슨"),
        RankingAdapter.RankingInfo(6, "문제라도?")
    )
}