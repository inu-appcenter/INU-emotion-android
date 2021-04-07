package com.inu.emotion.mvvm.feature.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R
import com.inu.emotion.mvvm.feature.betting.BettingTemperatureActivity
import com.inu.emotion.mvvm.feature.emotion.SelectEmotionActivity
import com.inu.emotion.mvvm.feature.login.LoginActivity
import com.inu.emotion.mvvm.feature.today.ResultActivity

class MenuAdapter(private val menuData: ArrayList<MenuVO>)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    val layoutIds = arrayListOf(SelectEmotionActivity::class.java, ResultActivity::class.java, BettingTemperatureActivity::class.java, null, LoginActivity::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_menu, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuData[position]
        holder.setImage(item.img)
        holder.tvMenu?.text = menuData[position].menu
        holder.setView.setOnClickListener{
            val layoutId = layoutIds[position]
            if(layoutId != null) {
                val intent = Intent(it.context, layoutId)
                it.context.startActivity(intent)
            }
            else Toast.makeText(it.context, "준비중입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return menuData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgViewMenu: ImageView? = null
        var tvMenu: TextView? = null
        val setView = view
        init {
            imgViewMenu = view.findViewById(R.id.img_menu)
            tvMenu = view.findViewById(R.id.tv_menu)
        }
        fun setImage(@DrawableRes imgId: Int) {
            imgViewMenu?.setImageResource(imgId)
        }
    }

    data class MenuVO(val menu: String, @DrawableRes val img: Int)
}