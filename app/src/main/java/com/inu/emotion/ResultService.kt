package com.inu.emotion

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultService {
    var temperature : Int
    @GET("result")
    fun getResult(): Call<ResultEntity?>
}