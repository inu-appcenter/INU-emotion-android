package com.inu.emotion.mvvm.feature.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

class ElementAdapter(private val elementList: ArrayList<String?>) : RecyclerView.Adapter<ElementAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calendar_element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ranking.text = elementList[position]
    }

    override fun getItemCount(): Int = elementList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ranking: TextView = view.findViewById(R.id.tv_item_element)
    }
}