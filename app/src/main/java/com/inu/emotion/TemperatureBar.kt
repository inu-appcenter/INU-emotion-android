package com.inu.emotion

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
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
        var curTemperature = start + (size - 90) * (100 / 100F)
        canvas?.drawLine(start, middle, curTemperature, middle, paint)

        // text 그리기
        /*
        paint.textSize = resources.getDimensionPixelSize(R.dimen.bar_textSize).toFloat()
        paint.strokeWidth = 8f
        paint.color = Color.BLACK
        var txt = this.progress.toString()
        canvas?.drawText(
                txt,
                width.toFloat() / 2,
                height.toFloat() / 2 - 15F,
                paint
        )

        txt = getText(this.progress)
        canvas?.drawText(
                txt,
                width.toFloat() / 2 - 5F,
                height.toFloat(),
                paint
        )*/
    }

    fun getText(progress: Int) : String = when {
        (this.progress <= 10) -> "슬픔"
        (progress <= 20) -> "짜증"
        (progress <= 30) -> "센치"
        (progress <= 40) -> "심심"
        (progress <= 50) -> "만족"
        (progress <= 60) -> "좋음"
        (progress <= 70) -> "기쁨"
        (progress <= 80) -> "행복"
        (progress <= 90) -> "나쁨"
        (progress <= 100) -> "분노"
        else -> "Error!!"
    }
}