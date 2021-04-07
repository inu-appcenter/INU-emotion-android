package com.inu.emotion.mvvm.feature.main

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.inu.emotion.R
import com.inu.emotion.mvvm.feature.betting.BettingTemperatureActivity
import com.inu.emotion.mvvm.feature.emotion.SelectEmotionActivity
import com.inu.emotion.mvvm.feature.login.LoginActivity
import com.inu.emotion.mvvm.feature.today.ResultActivity
import java.lang.Exception

class MainViewModel: ViewModel() {
    var name: String = "홍길동"
    var major: String = "정보기술대학 컴퓨터공학부"

    inner class BtnClickListener(val context: Context) : View.OnClickListener {

        override fun onClick(view: View?) {
            val intent = when(view?.id) {
                R.id.btn_select_emotion     -> Intent(view.context, SelectEmotionActivity::class.java)
                R.id.btn_result             -> Intent(view.context, ResultActivity::class.java)
                R.id.btn_betting_emotion    -> Intent(view.context, BettingTemperatureActivity::class.java)
                R.id.btn_emotion_graph      -> {
                    Toast.makeText(context, "준비중입니다.", Toast.LENGTH_LONG).show()
                    null
                }
                R.id.image_profile          -> Intent(view.context, LoginActivity::class.java)
                else                        -> throw Exception("BtnClickListener Error : unexpected id")
            }

            if(intent != null) context.startActivity(intent)
        }
    }
}