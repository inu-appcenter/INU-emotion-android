package com.inu.emotion

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ElementAdapter(private val dataSet: ArrayList<ElementVO>) :
        RecyclerView.Adapter<ElementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_elements, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.category.text = dataSet[position].category
        viewHolder.element1.text = dataSet[position].elements[0]
        viewHolder.element2.text = dataSet[position].elements[1]
        viewHolder.element3.text = dataSet[position].elements[2]
        viewHolder.element4.text = dataSet[position].elements[3]
        viewHolder.element5.text = dataSet[position].elements[4]
        viewHolder.element6.text = dataSet[position].elements[5]
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView
        val element1: TextView
        val element2: TextView
        val element3: TextView
        val element4: TextView
        val element5: TextView
        val element6: TextView

        init {
            category = view.findViewById(R.id.category)
            element1 = view.findViewById(R.id.element1)
            element2 = view.findViewById(R.id.element2)
            element3 = view.findViewById(R.id.element3)
            element4 = view.findViewById(R.id.element4)
            element5 = view.findViewById(R.id.element5)
            element6 = view.findViewById(R.id.element6)
        }
    }

    data class ElementVO(val category: String, val elements: ArrayList<String>)
}