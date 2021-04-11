package com.inu.emotion.mvvm.feature.calendar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

/**
 * days : 한 달 총 일 수
 * startDay : 첫 날 요일
 */
class CalendarAdapter(private val calendarInfo: ArrayList<CalendarVO>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calendar, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = calendarInfo[position].day
        val temperature = calendarInfo[position].temperature
        when {
            (temperature <= 20) -> holder.textView.backgroundTintList = holder.textView.context.resources.getColorStateList(R.color.under_20)
            (temperature <= 40) -> holder.textView.backgroundTintList = holder.textView.context.resources.getColorStateList(R.color.under_40)
            (temperature <= 60) -> holder.textView.backgroundTintList = holder.textView.context.resources.getColorStateList(R.color.under_60)
            (temperature <= 80) -> holder.textView.backgroundTintList = holder.textView.context.resources.getColorStateList(R.color.under_80)
            (temperature <= 100) -> holder.textView.backgroundTintList = holder.textView.context.resources.getColorStateList(R.color.over_80)
        }
    }

    override fun getItemCount(): Int = calendarInfo.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_item_calendar)
    }

    data class CalendarVO(val day: String?, var temperature: Int)
}