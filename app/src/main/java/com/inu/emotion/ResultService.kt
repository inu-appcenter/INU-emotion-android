package com.inu.emotion

import retrofit2.Call
import retrofit2.http.*

interface ResultService {
    @GET("result")
    fun getResult(): Call<ResultEntity?>

    @FormUrlEncoded
    @POST("temp")
    fun postResult(@Field("temp") message: Int) : Call<Unit>
}