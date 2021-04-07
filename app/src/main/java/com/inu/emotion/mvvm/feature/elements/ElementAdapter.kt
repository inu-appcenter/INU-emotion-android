package com.inu.emotion.mvvm.feature.elements

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R

class ElementAdapter(private val dataSet: ArrayList<ElementVO>) :
        RecyclerView.Adapter<ElementAdapter.ViewHolder>() {
    val imgIds = arrayListOf(R.drawable.img_element_food,
        R.drawable.img_element_relation,
        R.drawable.img_element_study,
        R.drawable.img_element_hobby,
        R.drawable.img_element_health,
        R.drawable.img_element_etc)
    var elements : ArrayList<String?> = ArrayList(3)
    val cntOfElement : Int
        get() = elements.size

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
        viewHolder.logo.setImageResource(imgIds[position])
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.category)
        val element1: TextView = view.findViewById(R.id.element1)
        val element2: TextView = view.findViewById(R.id.element2)
        val element3: TextView = view.findViewById(R.id.element3)
        val element4: TextView = view.findViewById(R.id.element4)
        val element5: TextView = view.findViewById(R.id.element5)
        val element6: TextView = view.findViewById(R.id.element6)
        val logo: ImageView = view.findViewById(R.id.img_element)

        init {
            setOnClickListener()
        }

        private fun setOnClickListener() {
            val onClickListener = OnClickListener()
            element1.setOnClickListener(onClickListener)
            element2.setOnClickListener(onClickListener)
            element3.setOnClickListener(onClickListener)
            element4.setOnClickListener(onClickListener)
            element5.setOnClickListener(onClickListener)
            element6.setOnClickListener(onClickListener)
        }

        inner class OnClickListener : View.OnClickListener {
            override fun onClick(view: View?) {
                if((view as TextView).typeface == Typeface.DEFAULT) {
                    // 이미 3개를 선택했을 경우
                    if(cntOfElement == 3) {
                        Toast.makeText(view?.context, "최대 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                        return
                    }

                    elements.add(view.text.toString())
                    view.typeface = Typeface.DEFAULT_BOLD
                }
                else {
                    elements.remove(view.text.toString())
                    view.typeface = Typeface.DEFAULT
                }
            }
        }
    }

    data class ElementVO(val category: String, val elements: ArrayList<String>)
}