package com.inu.emotion.mvvm.feature.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val arrayList = getDummyData()

        // 캘린더 RecyclerView setting
        val calendarRecyclerView = findViewById<RecyclerView>(R.id.calendar_recyclerview)
        calendarRecyclerView.addItemDecoration(CalendarItemDecoration(7,
                resources.getDimensionPixelSize(R.dimen.interval_calendar_item),
                false))
        (calendarRecyclerView.layoutManager as GridLayoutManager).apply { spanCount = 7 }
        calendarRecyclerView.adapter = CalendarAdapter(arrayList)

        // 감정 요소 RecyclerView setting
        val dummyList: ArrayList<String?> = arrayListOf("집밥", "가족", "드라마")
        val elementRecyclerView = findViewById<RecyclerView>(R.id.calendar_element_recyclerview)
        elementRecyclerView.adapter = ElementAdapter(dummyList)

        // undo button set onClickListener
        findViewById<ImageView>(R.id.calendar_undo).setOnClickListener { finish() }
    }

    fun getDummyData(): ArrayList<CalendarAdapter.CalendarVO> {
        val arrayList = arrayListOf(
                CalendarAdapter.CalendarVO("월", 101),
                CalendarAdapter.CalendarVO("화", 101),
                CalendarAdapter.CalendarVO("수", 101),
                CalendarAdapter.CalendarVO("목", 101),
                CalendarAdapter.CalendarVO("금", 101),
                CalendarAdapter.CalendarVO("토", 101),
                CalendarAdapter.CalendarVO("일", 101)
        )
        for(i in 0 until 4) arrayList.add(CalendarAdapter.CalendarVO("", 101))
        for(i in 1..30) arrayList.add(CalendarAdapter.CalendarVO(i.toString(), 101))
        arrayList[7 + 4 + 0].temperature = 0
        arrayList[7 + 4 + 1].temperature = 30
        arrayList[7 + 4 + 2].temperature = 50
        arrayList[7 + 4 + 3].temperature = 70
        arrayList[7 + 4 + 4].temperature = 90

        return arrayList
    }
}