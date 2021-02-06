package com.inu.emotion

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
            width = resources.getDimensionPixelSize(R.dimen.bar_stroke_size)
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            height = resources.getDimensionPixelSize(R.dimen.bar_size)
        }
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
        canvas?.drawLine(0F, 0F, 0F, 500F, paint)


        // text 그리기
        paint.textSize = resources.getDimensionPixelSize(R.dimen.bar_textSize).toFloat()
        paint.strokeWidth = 8f
        paint.color = Color.BLACK
        val txt = progress.toString()
        canvas?.drawText(
            txt,
            (width / 2 - (paint.measureText(txt) / 2).toInt()).toFloat(),
            (height / 2 - (paint.descent() + paint.ascent()) / 2),
            paint
        )
    }


}