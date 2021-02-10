package com.inu.emotion

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

class TemperatureBar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet
) : androidx.appcompat.widget.AppCompatSeekBar(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            width = resources.getDimensionPixelSize(R.dimen.bar_size)
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            height = resources.getDimensionPixelSize(R.dimen.bar_stroke_size)
        }

        // 로테이션 버그 때문에 setMeasuredDimension(width, height) 이 코드를 사용하면 안 됨.
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val size = resources.getDimensionPixelSize(R.dimen.bar_size)
        var strokeSize = resources.getDimensionPixelSize(R.dimen.bar_stroke_size)
        val paint = Paint()


        // 막대 그리기
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = resources.getDimensionPixelSize(R.dimen.bar_stroke_size).toFloat()
        paint.color = Color.RED
        val middle = (strokeSize / 2).toFloat() // y 좌표 보정
        val start = 50F // x 좌표 보정
        var curTemperature = start + (size - 90) * (this.progress / 100F)
        canvas?.drawLine(start, middle, curTemperature, middle, paint)
    }
}