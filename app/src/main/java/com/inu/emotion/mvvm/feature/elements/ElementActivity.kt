package com.inu.emotion.mvvm.feature.elements

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inu.emotion.R
import com.inu.emotion.databinding.ActivityElementBinding
import com.inu.emotion.mvvm.global.DataStorage

class ElementActivity : AppCompatActivity() {
    private val viewModel: ElementViewModel by viewModels()
    private lateinit var binding: ActivityElementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_element)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // undo
        findViewById<ImageView>(R.id.undo).setOnClickListener{finish()}

        // 리사이클러뷰 설정
        val recyclerView = findViewById<RecyclerView>(R.id.elements)
        recyclerView.addItemDecoration(ItemDecoration(this))

        // 버튼 클릭
        findViewById<Button>(R.id.btn_ok).setOnClickListener {
            // DataStorage 에 elements 를 저장
            val elements = getSelectedElements((recyclerView.adapter as ElementAdapter).elements)
            DataStorage.insertElements(elements)

            viewModel.postMood()

            // 메인화면으로 이동
            val resultIntent = Intent(this, ElementActivity::class.java)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    // 선택항목이 3개가 아닐 시 널 문자를 추가하여 반환
    private fun getSelectedElements(elements: ArrayList<String?>) : ArrayList<String?> {
        for (i in 0 until (3 - elements.size)) {
            elements.add(null)
        }

        return elements
    }

    class ItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = context.resources.getDimensionPixelSize(R.dimen.item_decoration_space)
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("elements")
        fun insertElements(view: RecyclerView, data: ArrayList<ElementAdapter.ElementVO>) {
            view.adapter = ElementAdapter(data)
        }
    }
}