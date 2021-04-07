package com.inu.emotion.mvvm.feature.emotion

import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inu.emotion.R

class EmotionViewModel : ViewModel() {
    private val _temperature = MutableLiveData("50ºC")
    val temperature: LiveData<String> = _temperature

    inner class OnTemperatureBarChangeListener(private val view: View) : SeekBar.OnSeekBarChangeListener {
        var preId = R.id.right_txt3

        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(view: SeekBar?) {}
        override fun onProgressChanged(v: SeekBar?, progress: Int, p2: Boolean) {
            if(v == null) Log.d("SelectEmotionActivity : ", "error : view is null")
            else {
                val selectedId = when {
                    (progress <= 10) -> R.id.right_txt5
                    (progress <= 20) -> R.id.left_txt5
                    (progress <= 30) -> R.id.right_txt4
                    (progress <= 40) -> R.id.left_txt4
                    (progress <= 50) -> R.id.right_txt3
                    (progress <= 60) -> R.id.left_txt3
                    (progress <= 70) -> R.id.right_txt2
                    (progress <= 80) -> R.id.left_txt2
                    (progress <= 90) -> R.id.right_txt1
                    (progress <= 100) -> R.id.left_txt1
                    else -> null
                }

                view.findViewById<TextView>(preId).textSize = 17F
                view.findViewById<TextView>(preId).setTypeface(null, Typeface.NORMAL)
                view.findViewById<TextView>(selectedId!!).textSize = 24F
                view.findViewById<TextView>(selectedId!!).setTypeface(null, Typeface.BOLD)
                preId = selectedId

                _temperature.value = progress.toString() + "ºC"
            }
        }
    }
}