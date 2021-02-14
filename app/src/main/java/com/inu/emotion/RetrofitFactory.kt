package com.inu.emotion

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    val BASE_URL = "http://127.0.0.1:80/"

    fun create() : ResultService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ResultService::class.java)
    }
}