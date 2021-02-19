package com.inu.emotion

import retrofit2.Call
import retrofit2.http.*

interface ResultService {
    @GET("result")
    fun getResult(): Call<ResultEntity?>

    @FormUrlEncoded
    @POST("mood")
    fun postResult(@Field("select_mood") temperature: Int,
                   @Field("element_first") element_first: String? = null,
                   @Field("element_second") element_second: String? = null,
                   @Field("element_third") element_third: String? = null) : Call<Unit>
}