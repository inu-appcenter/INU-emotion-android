package com.inu.emotion

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultView = findViewById<TextView>(R.id.tv_result)

        val retrofitFactory = RetrofitFactory().create()
        val call = retrofitFactory.getResult()
        var resultBody : ResultService? = null

        val queue = Volley.newRequestQueue(this)
        val url = "http://127.0.0.1/result"
        val stringRequest = StringRequest(Request.Method.GET, url,
            com.android.volley.Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                resultView.text = "Response is: ${response.substring(0, 500)}"
            },
            com.android.volley.Response.ErrorListener { resultView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        Thread {
            call.enqueue(object : retrofit2.Callback<ResultService?> {
                override fun onResponse(call: Call<ResultService?>?, response: Response<ResultService?>?) {
                    resultBody = response?.body()
                    resultView.text = resultBody?.temperature.toString()
                    Log.i("ResultActivity : ", "성공")
                }

                override fun onFailure(call: Call<ResultService?>?, t: Throwable?) {
                    Log.i("ResultActivity : ", "fail")
                }
            })
            //Log.i("resultActivity : ", resultBody?.temperature.toString())
        }.run()
        /*
        call.enqueue(object : retrofit2.Callback<ResultService?> {
            override fun onResponse(call: Call<ResultService?>?, response: Response<ResultService?>?) {
                resultBody = response.body()
            }

            override fun onFailure(call: Call<ResultService?>?, t: Throwable?) {
                Log.i("ResultActivity : ", "fail")
            }
        })
*/
    }
}
/*
override fun onResponse(call: Call<ResultEntity>?, response: Response<ResultEntity>?) {
                if(response!!.isSuccessful()) {
                    resultBody = response.body()
                }
                else {
                    Log.i("ResultActivity : ", "fail")
                }
            }

            override fun onFailure(call: Call<ResultEntity>?, t: Throwable?) {
                //
            }
* */