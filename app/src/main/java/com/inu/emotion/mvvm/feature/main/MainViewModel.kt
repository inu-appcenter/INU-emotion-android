package com.inu.emotion.mvvm.feature.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inu.emotion.mvvm.feature.signup.SignUpActivity
import com.inu.emotion.mvvm.global.TokenStorage
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import com.inu.emotion.mvvm.model.network.UserEntity
import retrofit2.Call
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val _name = MutableLiveData("로그인 하려면 프로필을...")
    val name: LiveData<String?> = _name

    fun getAndSetName() {
        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.getUserInfo("Bearer " + TokenStorage.token)
        call.enqueue(object : retrofit2.Callback<UserEntity?> {
            override fun onResponse(call: Call<UserEntity?>, response: Response<UserEntity?>) {
                val result = response.body()
                Log.i("유저 정보 요청 : ", "성공")
                Log.i("유저 정보 요청 res message : ", response.message())
                Log.i("유저 정보 요청 res code : ", response.code().toString())
                _name.value = result?.nick
            }

            override fun onFailure(call: Call<UserEntity?>, t: Throwable) {
                t.printStackTrace()
                Log.i("유저 정보 요청 : ", "fail")
            }
        })
    }
}