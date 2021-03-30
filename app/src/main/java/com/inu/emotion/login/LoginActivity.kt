package com.inu.emotion.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.inu.emotion.R
import com.inu.emotion.network.LoginEntity
import com.inu.emotion.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 버튼 클릭
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            // id / pw 얻기
            val inputId = findViewById<EditText>(R.id.input_id).text.toString()
            val inputPw = findViewById<EditText>(R.id.input_password).text.toString()
            Log.i("123123 : ", inputId + ", " + inputPw)
            // 로그인 요청
            val retrofitFactory = RetrofitFactory().create()
            val call = retrofitFactory.postLogin(inputId, inputPw)
            var resultToken : LoginEntity? = null
            call.enqueue(object : retrofit2.Callback<LoginEntity> {
                override fun onResponse(call: Call<LoginEntity>?, response: Response<LoginEntity>?) {
                    if (response!!.isSuccessful) {
                        Log.i("LoginActivity : ", "login 성공")
                        resultToken = response.body()
                        Log.i("loginToken : ", resultToken.toString())
                    } else {
                        Log.e("연결 실패 : ", "error code : " + response.code())
                        Log.e("LoginActivity res message: ", response.message())
                        findViewById<TextView>(R.id.text_login).visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<LoginEntity>?, t: Throwable?) {
                    t?.printStackTrace()
                    Log.e("LoginActivity : ", "연결 실패 (onFailure)")
                }
            })
        }
    }
}

