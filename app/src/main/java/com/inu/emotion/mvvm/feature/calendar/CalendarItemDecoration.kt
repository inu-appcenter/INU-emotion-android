package com.inu.emotion.mvvm.feature.calendar

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// 스택 오버 플로우에서 가져온 거 또 복사함
class CalendarItemDecoration(spanCount: Int, spacing: Int, includeEdge: Boolean)
    :RecyclerView.ItemDecoration() {
        private val spanCount: Int = spanCount
        private val spacing: Int = spacing
        private val includeEdge: Boolean = includeEdge

        override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount                   // item column
            if (includeEdge) {
                outRect.left =
                        spacing - column * spacing / spanCount      // spacing - column * ((1f / spanCount) * spacing)
                outRect.right =
                        (column + 1) * spacing / spanCount          // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) {                     // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing                        // item bottom
            } else {
                outRect.left = column * spacing / spanCount     // column * ((1f / spanCount) * spacing)
                outRect.right =
                        spacing - (column + 1) * spacing / spanCount// spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing                       // item top
                }
            }
        }
}