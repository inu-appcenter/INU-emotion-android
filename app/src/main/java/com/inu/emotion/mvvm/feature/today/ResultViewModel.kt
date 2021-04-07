package com.inu.emotion.mvvm.feature.today

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.inu.emotion.R
import com.inu.emotion.mvvm.feature.common.TemperatureBar
import com.inu.emotion.mvvm.model.network.ResultEntity
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class ResultViewModel : ViewModel() {
    var temperature: String = 99.toString() + "ºC"

    fun request(view: View) {
        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.getResult()
        var resultBody : ResultEntity?
        val thermometer = view.findViewById<TemperatureBar>(R.id.thermometer)

        call.enqueue(object : retrofit2.Callback<ResultEntity?> {
            override fun onResponse(call: Call<ResultEntity?>, response: Response<ResultEntity?>) {
                resultBody = response.body()
                Log.i("오늘의 온도 요청 : ", "성공")
                Log.i("오늘의 온도 요청 res message : ", response.message())
                Log.i("오늘의 온도 요청 res code : ", response.code().toString())
                thermometer.progress = resultBody!!.todayMoodAvg
                temperature = thermometer.progress.toString() + "ºC"
                Log.i("오늘의 온도 요청 : ", "server result - " + resultBody!!.elementRanking)
            }

            override fun onFailure(call: Call<ResultEntity?>, t: Throwable?) {
                Log.i("오늘의 온도 요청 : ", "fail")
                t?.printStackTrace()
            }
        })
    }
}