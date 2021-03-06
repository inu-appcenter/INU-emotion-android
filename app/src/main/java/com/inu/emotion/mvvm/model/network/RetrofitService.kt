package com.inu.emotion.mvvm.model.network

import com.inu.emotion.mvvm.feature.signup.SignUpActivity
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("mood")
    fun getResult(@Header("authorization") token: String?): Call<ResultEntity?>

    @GET("main")
    fun getUserInfo(@Header("authorization") token: String?): Call<UserEntity?>

    @FormUrlEncoded
    @POST("mood")
    fun postMood(@Header("authorization") token: String?,
                 @Field("select_mood") temperature: Int,
                 @Field("element_first") element_first: String? = null,
                 @Field("element_second") element_second: String? = null,
                 @Field("element_third") element_third: String? = null) : Call<Unit>

    @FormUrlEncoded
    @POST("betting")
    fun postBetting(@Header("authorization") token: String?,
                    @Field("bet_mood") temperature: Int) : Call<Unit>

    /**
     * res {success, message, token}
     */
    @FormUrlEncoded
    @POST("auth/login")
    fun postLogin(@Field("email") id: String,
                  @Field("password") password: String) : Call<LoginEntity>

    @FormUrlEncoded
    @POST("auth/register")
    fun postSignUp(@Field("email") id: String,
                   @Field("nick") nickname: String,
                   @Field("password") password: String) : Call<SignUpActivity.SignUpResponse>
}