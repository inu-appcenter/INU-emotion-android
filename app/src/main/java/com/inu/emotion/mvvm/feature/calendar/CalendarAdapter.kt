package com.inu.emotion.mvvm.feature.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

/**
 * days : 한 달 총 일 수
 * startDay : 첫 날 요일
 */
class CalendarAdapter(private val days: Int,private val startDay: Int) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    private val calendarInfo = ArrayList<String?>(42)
    init{
        calendarInfo.add("월")
        calendarInfo.add("화")
        calendarInfo.add("수")
        calendarInfo.add("목")
        calendarInfo.add("금")
        calendarInfo.add("토")
        calendarInfo.add("일")
        for(i in 0 until startDay) {
            calendarInfo.add(null)
        }
        for(i in 1..days) {
            calendarInfo.add(i.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_menu, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = calendarInfo[position]
    }

    override fun getItemCount(): Int = calendarInfo.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById<TextView>(R.id.tv_item_calendar)
    }
}