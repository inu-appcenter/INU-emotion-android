package com.inu.emotion

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.getResult()
        var resultBody : ResultEntity? = null
        val temperatureBar = findViewById<TemperatureBar>(R.id.thermometer)
        val temp = findViewById<TextView>(R.id.tv_temperature)

        // SeekBar 터치 불가
        temperatureBar.isEnabled = false


        call.enqueue(object : retrofit2.Callback<ResultEntity?> {
            override fun onResponse(call: Call<ResultEntity?>, response: Response<ResultEntity?>) {
                resultBody = response?.body()
                Log.i("ResultActivity : ", "성공")
                temperatureBar.progress = resultBody!!.temperature
                temp.text = temperatureBar.progress.toString() + "ºC"
            }

            override fun onFailure(call: Call<ResultEntity?>, t: Throwable?) {
                Log.i("ResultActivity : ", "fail")
                t?.printStackTrace()
            }
        })
    }
}