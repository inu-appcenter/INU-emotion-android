package com.inu.emotion.mvvm.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

class MenuAdapter(private val menuData: ArrayList<MenuVO>)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_menu, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuData[position]
        holder.setImage(item.img)
        holder.tvMenu?.text = menuData[position].menu
    }

    override fun getItemCount(): Int {
        return menuData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgViewMenu: ImageView? = null
        var tvMenu: TextView? = null
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