package com.inu.emotion.mvvm.global

/**
 * EmotionActivity, ElementActivity 에서 발생한 데이터를 저장하기 위한 클래스
 */
object DataStorage {
    var temperature: Int = 50
    val elements: ArrayList<String?> = ArrayList(3)

    fun insertElements(elements: ArrayList<String?>) {
        for(i in 0 until 3) {
            this.elements[i] = elements[i]
        }
    }
}