package com.inu.emotion.mvvm.feature.elements

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R
import com.inu.emotion.mvvm.global.TokenStorage
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class SelectElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_element)

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener{finish()}

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
            val elements = getElements((recyclerView.adapter as ElementAdapter).elements)
            val call = retrofitFactory.postMood(
                    token = "Bearer " + TokenStorage.token,
                    temperature = temperature,
                    element_first = elements[0],
                    element_second = elements[1],
                    element_third = elements[2])
            call.enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                    if(response!!.isSuccessful) {
                        Log.i("온도, 요소 제출 : ", "성공")
                        Log.i("온도, 요소 제출 : ", "response code : " + response.code())
                        Log.i("온도, 요소 제출 : ", "message : " + response.message())
                    }
                    else {
                        Log.e("온도, 요소 제출 : ", "error code : " + response.code())
                        Log.e("온도, 요소 제출 : ", "message : " + response.message())
                    }
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    t?.printStackTrace()
                    Log.e("SelectEmotionActivity : ", "연결 실패 (서버쪽 문제일 가능성이 높음)")
                }
            })

            // 메인화면으로 이동
            val resultIntent = Intent(this, SelectElementActivity::class.java)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    // 선택항목이 3개가 아닐 시 널 문자를 추가하여 반환
    fun getElements(elements: ArrayList<String?>) : ArrayList<String?> {
        for (i in 0 until (3 - elements.size)) {
            elements.add(null)
        }

        return elements
    }

    private fun insertDataSet() : ArrayList<ElementAdapter.ElementVO> {
        return arrayListOf<ElementAdapter.ElementVO>(
                ElementAdapter.ElementVO("음식",
                        resources.getStringArray(R.array.element_foods).toCollection(ArrayList())),
                ElementAdapter.ElementVO("인간관계",
                        resources.getStringArray(R.array.elements_relation).toCollection(ArrayList())),
                ElementAdapter.ElementVO("학업",
                        resources.getStringArray(R.array.elements_study).toCollection(ArrayList())),
                ElementAdapter.ElementVO("취미",
                        resources.getStringArray(R.array.elements_hobby).toCollection(ArrayList())),
                ElementAdapter.ElementVO("건강",
                        resources.getStringArray(R.array.elements_health).toCollection(ArrayList())),
                ElementAdapter.ElementVO("기타",
                        resources.getStringArray(R.array.elements_etc).toCollection(ArrayList())),
        )
    }

    class ItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = context.resources.getDimensionPixelSize(R.dimen.item_decoration_space)
        }
    }
}