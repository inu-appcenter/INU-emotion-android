package com.inu.emotion

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class SelectElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_element)

        // 리사이클러뷰 설정
        val recyclerView = findViewById<RecyclerView>(R.id.elements)
        val dataSet = insertDataSet()
        recyclerView.addItemDecoration(ItemDecoration(this))
        recyclerView.adapter = ElementAdapter(dataSet)

        // 버튼 클릭
        findViewById<Button>(R.id.btn_ok).setOnClickListener {
            // 서버로 데이터 전송
            val retrofitFactory = RetrofitFactory().create()
            val temperature = intent.getIntExtra("temp", 50)
            val call = retrofitFactory.postResult(temperature = temperature)
            call.enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                    if(response!!.isSuccessful) {
                        Log.i("SelectEmotionActivity : ", "post 성공")
                    }
                    else {
                        Log.e("연결 실패 : ", "error code : " + response.code())
                    }
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    t?.printStackTrace()
                    Log.e("SelectEmotionActivity : ", "연결 실패 (서버쪽 문제일 가능성이 높음)")
                }
            })

            // 메인화면으로 이동
            finish()
        }
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