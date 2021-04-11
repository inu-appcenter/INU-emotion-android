package com.inu.emotion.mvvm.feature.elements

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R
import com.inu.emotion.mvvm.global.DataStorage
import com.inu.emotion.mvvm.global.TokenStorage
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class ElementViewModel(application: Application) : AndroidViewModel(application) {
    // 서버로 데이터 전송
    fun postMood() {
        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.postMood(
                token = TokenStorage.token,
                temperature = DataStorage.temperature,
                element_first = DataStorage.elements[0],
                element_second = DataStorage.elements[1],
                element_third = DataStorage.elements[2])
        call.enqueue(object : retrofit2.Callback<Unit> {
            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                if(response!!.isSuccessful) {
                    Log.i("온도, 요소 제출 : ", DataStorage.toString())
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
    }

    fun getElementList() : ArrayList<ElementAdapter.ElementVO> {
        return arrayListOf(
                ElementAdapter.ElementVO("음식",
                        getApplication<Application>().resources.getStringArray(R.array.element_foods).toCollection(ArrayList())),
                ElementAdapter.ElementVO("인간관계",
                        getApplication<Application>().resources.getStringArray(R.array.elements_relation).toCollection(ArrayList())),
                ElementAdapter.ElementVO("학업",
                        getApplication<Application>().resources.getStringArray(R.array.elements_study).toCollection(ArrayList())),
                ElementAdapter.ElementVO("취미",
                        getApplication<Application>().resources.getStringArray(R.array.elements_hobby).toCollection(ArrayList())),
                ElementAdapter.ElementVO("건강",
                        getApplication<Application>().resources.getStringArray(R.array.elements_health).toCollection(ArrayList())),
                ElementAdapter.ElementVO("기타",
                        getApplication<Application>().resources.getStringArray(R.array.elements_etc).toCollection(ArrayList())),
        )
    }
}

