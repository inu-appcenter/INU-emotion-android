package com.inu.emotion

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.content.ContextCompat

class TemperatureBar constructor(
        context: Context,
        attrs: AttributeSet
) : androidx.appcompat.widget.AppCompatSeekBar(context, attrs) {
    val temperatureColor : ArrayList<Int> = ArrayList(5)
    init {
        temperatureColor.add(ContextCompat.getColor(context, R.color.under_20))
        temperatureColor.add(ContextCompat.getColor(context, R.color.under_40))
        temperatureColor.add(ContextCompat.getColor(context, R.color.under_60))
        temperatureColor.add(ContextCompat.getColor(context, R.color.under_80))
        temperatureColor.add(ContextCompat.getColor(context, R.color.over_80))
    }

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
        val strokeSize = resources.getDimensionPixelSize(R.dimen.bar_stroke_size)
        val paint = Paint()

        // 막대 그리기
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = resources.getDimensionPixelSize(R.dimen.bar_stroke_size).toFloat()

        val middle = (strokeSize / 2).toFloat() // y 좌표 보정
        var start = 50F // x 좌표 보정

        val curTemperature = start + (size - 90) * (this.progress / 100F)
        var end : Float

        for(i in 0 until 5) {
            if(this.progress <= i * 20) break
            paint.color = temperatureColor[i]
            end = start + (size - 90) * (20F / 100F)
            canvas?.drawLine(start, middle, curTemperature, middle, paint)
            start = end
        }
    }
}