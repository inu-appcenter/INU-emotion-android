package com.inu.emotion.mvvm.feature.login

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inu.emotion.R
import com.inu.emotion.mvvm.global.TokenStorage
import com.inu.emotion.mvvm.model.network.LoginEntity
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _isSuccessful = MutableLiveData(true)
    val isSuccessful: LiveData<Boolean> = _isSuccessful

    fun requestLogin(id: String, pw: String, onSuccess: ()->Any?) {
        Log.i("로그인 요청 : ", id + ", " + pw)

        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.postLogin(id, pw)
        var result : LoginEntity?
        call.enqueue(object : retrofit2.Callback<LoginEntity> {
            override fun onResponse(call: Call<LoginEntity>?, response: Response<LoginEntity>?) {
                _isSuccessful.value = response!!.isSuccessful
                if (response.isSuccessful) {
                    result = response.body()
                    TokenStorage.token = result?.token
                    Log.i("로그인 요청 : ", "login 성공")
                    Log.i("로그인 요청 : ", "response code : " + response.code())
                    Log.i("로그인 요청 response message : ", response.message())
                    Log.i("로그인 토큰 : ", result?.token.toString())
                    onSuccess()
                } else {
                    Log.e("로그인 요청 : ", "error code : " + response.code())
                    Log.e("로그인 요청 res message : ", response.message())
                }
            }

            override fun onFailure(call: Call<LoginEntity>?, t: Throwable?) {
                t?.printStackTrace()
                Log.e("로그인 요청 : ", "연결 실패 (onFailure)")
            }
        })
    }
}