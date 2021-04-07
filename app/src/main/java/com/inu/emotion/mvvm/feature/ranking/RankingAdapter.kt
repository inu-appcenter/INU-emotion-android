package com.inu.emotion.mvvm.feature.ranking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R
import com.inu.emotion.mvvm.feature.main.MenuAdapter

class RankingAdapter(private val rankingList: ArrayList<RankingInfo>)
    : RecyclerView.Adapter<RankingAdapter.RankingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ranking, parent, false)
        return RankingHolder(view)
    }

    override fun onBindViewHolder(holder: RankingHolder, position: Int) {
        holder.ranking.text = rankingList[position].ranking.toString()
        holder.nickname.text = rankingList[position].nickname
    }

    override fun getItemCount(): Int = rankingList.size

    inner class RankingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ranking: TextView = view.findViewById(R.id.ranking)
        val nickname: TextView = view.findViewById(R.id.nickname)
    }

    data class RankingInfo(val ranking: Int, val nickname: String)
}