package com.inu.emotion

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener{finish()}

        // SeekBar 터치 불가
        val temperatureBar = findViewById<TemperatureBar>(R.id.thermometer)
        temperatureBar.isEnabled = false

        // 서버로부터 결과 온도 받은 후 출력
        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.getResult()
        var resultBody : ResultEntity?
        val thermometer = findViewById<TextView>(R.id.tv_temperature)

        call.enqueue(object : retrofit2.Callback<ResultEntity?> {
            override fun onResponse(call: Call<ResultEntity?>, response: Response<ResultEntity?>) {
                resultBody = response.body()
                Log.i("ResultActivity : ", "성공")
                temperatureBar.progress = resultBody!!.todayMoodAvg
                thermometer.text = temperatureBar.progress.toString() + "ºC"
                // TODO : 요소 순위 출력
                Log.i("ResultActivity : ", "server result - " + resultBody!!.elementRanking)
            }

            override fun onFailure(call: Call<ResultEntity?>, t: Throwable?) {
                Log.i("ResultActivity : ", "fail")
                t?.printStackTrace()
            }
        })

        // 랭킹 확인 버튼 클릭
        findViewById<Button>(R.id.btn_score_board).setOnClickListener {
            Toast.makeText(applicationContext, "서비스 준비중입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}