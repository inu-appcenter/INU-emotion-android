package com.inu.emotion.mvvm.feature.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.inu.emotion.R
import com.inu.emotion.mvvm.global.TokenStorage
import com.inu.emotion.mvvm.model.network.LoginEntity
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        findViewById<Button>(R.id.btn_sign_up).setOnClickListener {
            val id = findViewById<TextView>(R.id.input_id).toString()
            val pw = findViewById<TextView>(R.id.input_password).toString()
            val nickname = findViewById<TextView>(R.id.input_name).toString()

            val retrofitFactory = RetrofitFactory().create()
            val call = retrofitFactory.postSignUp(id, nickname, pw)
            call.enqueue(object : retrofit2.Callback<SignUpResponse> {
                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                    var result: SignUpResponse? = response.body()
                    if (response.isSuccessful) {
                        Log.i("회원 가입 요청 : ", "성공")
                        Log.i("회원 가입 요청 : ", "response code : " + response.code())
                        Log.i("회원 가입 요청 response message : ", response.message())
                        showToast(true)
                        finish()
                    } else {
                        Log.e("회원 가입 요청 : ", "error code : " + response.code())
                        Log.e("회원 가입 요청 res message : ", response.message())
                        Log.i("회원 가입 요청 response message : ", result?.message.toString())
                        showToast(false)
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("회원 가입 요청 : ", "연결 실패(onFailure)")
                    showToast(false)
                }
            })
        }
    }

    fun showToast(isSuccessful: Boolean) {
        if(isSuccessful) Toast.makeText(this, "회원 가입 성공", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "회원 가입 실패", Toast.LENGTH_SHORT).show()
    }

    data class SignUpResponse(val message: String?)
}