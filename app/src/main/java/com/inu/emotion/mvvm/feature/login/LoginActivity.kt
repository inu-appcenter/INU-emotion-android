package com.inu.emotion.mvvm.feature.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.inu.emotion.R
import com.inu.emotion.databinding.ActivityLoginBinding
import com.inu.emotion.mvvm.model.network.LoginEntity
import com.inu.emotion.mvvm.model.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // 버튼 클릭
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            // id / pw 얻기
            val inputId = findViewById<EditText>(R.id.input_id).text.toString()
            val inputPw = findViewById<EditText>(R.id.input_password).text.toString()

            // 로그인 요청
            viewModel.requestLogin(binding.root, inputId, inputPw)
        }
    }
}