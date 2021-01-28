package com.inu.emotion

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_select_emotion).setOnClickListener(BtnClickListener())
    }

    /* TODO : 메뉴
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }
    */

    class BtnClickListener : View.OnClickListener {
        override fun onClick(view: View?) {
            Toast.makeText(view?.context, "Button Clicked!", Toast.LENGTH_LONG).show()
        }
    }
}