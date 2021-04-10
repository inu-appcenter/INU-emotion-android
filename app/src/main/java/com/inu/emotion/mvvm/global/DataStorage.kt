package com.inu.emotion.mvvm.global

/**
 * EmotionActivity, ElementActivity 에서 발생한 데이터를 저장하기 위한 클래스
 */
object DataStorage {
    var temperature: Int = 50
    var elements: ArrayList<String?> = ArrayList()

    fun insertElements(elements: ArrayList<String?>) {
        this.elements = elements
    }

    override fun toString(): String {
        return """
            온도 : ${temperature}
            요소1 : ${elements[0]}
            요소2 : ${elements[1]}
            요소3 : ${elements[2]}
        """.trimIndent()
    }
}