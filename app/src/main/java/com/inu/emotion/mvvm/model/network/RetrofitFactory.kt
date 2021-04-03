package com.inu.emotion.mvvm.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    val BASE_URL = "http://13.209.236.78:2000/"

    fun create() : ResultService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ResultService::class.java)
    }
}