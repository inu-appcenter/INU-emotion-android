package com.inu.emotion

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SelectElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_element)

        val recyclerView = findViewById<RecyclerView>(R.id.elements)
        val dataSet = insertDataSet()
        recyclerView.addItemDecoration(ItemDecoration(this))
        recyclerView.adapter = ElementAdapter(dataSet)
    }

    private fun insertDataSet() : ArrayList<ElementAdapter.ElementVO> {
        return arrayListOf<ElementAdapter.ElementVO>(
                ElementAdapter.ElementVO("음식",
                        arrayListOf("집밥", "패스트푸드", "건강식", "외식", "배달", "디저트")),
                ElementAdapter.ElementVO("인간관계",
                        arrayListOf("가족", "친구", "연인", "동료", "동기", "선후배")),
                ElementAdapter.ElementVO("학업",
                        arrayListOf("시험", "과제/레포트", "성적", "공모전/대외활동", "자격증", "또뭐가있을까요")),
                ElementAdapter.ElementVO("취미",
                        arrayListOf("영화/드라마", "게임", "독서", "여행", "요리", "휴식")),
                ElementAdapter.ElementVO("건강",
                        arrayListOf("운동", "수면", "질병", "스트레스", "체중", "또뭐가있을까요")),
                ElementAdapter.ElementVO("기타",
                        arrayListOf("업무", "날씨", "교통", "행운", "불운", "그냥")),
        )
    }

    class ItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = context.resources.getDimensionPixelSize(R.dimen.item_decoration_space)
        }
    }
}