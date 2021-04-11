package com.inu.emotion.mvvm.feature.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val recyclerView = findViewById<RecyclerView>(R.id.calendar_recyclerview)
        recyclerView.addItemDecoration(CalendarItemDecoration(7, 10, false))
        recyclerView.adapter = CalendarAdapter(30, 4)
    }
}