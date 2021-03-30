package com.inu.emotion.betting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.inu.emotion.R
import com.inu.emotion.customView.TemperatureBar
import com.inu.emotion.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class BettingTemperatureActivity : AppCompatActivity() {
    var temperatureBar : TemperatureBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_betting_temperature)

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener{finish()}

        // 온도계 설정
        temperatureBar = findViewById(R.id.thermometer)
        temperatureBar?.setOnSeekBarChangeListener(OnTemperatureBarChangeListener())

        // 베팅 버튼 설정
        val bettingButton = findViewById<Button>(R.id.btn_betting)
        bettingButton.setOnClickListener(OnBettingButtonClickListener())
    }

    // 온도계 터치
    inner class OnTemperatureBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(view: SeekBar?) {}
        override fun onProgressChanged(view: SeekBar?, p1: Int, p2: Boolean) {
            var progress: Int

            if (view == null) Log.d("BettingActivity : ", "error : view is null")
            else {
                progress = view.progress
                findViewById<TextView>(R.id.tv_temperature).setText(progress.toString() + "ºC")
            }
        }
    }

    inner class OnBettingButtonClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            Toast.makeText(view?.context, "준비중입니다.", Toast.LENGTH_SHORT).show()

            val retrofitFactory = RetrofitFactory().create()
            val call = retrofitFactory.postBetting(temperatureBar!!.progress)
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
        }
    }
}