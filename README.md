# INU-emotion-android
감정 온도 투표 앱입니다.

## 이렇게 사용합니다.

<메인 화면><br/>
 - <로그인><br/>
 - <온도 선택> -> <요소 선택> : 이 온도를 선택하게 된 이유는 (요소1), (요소2), (요소3) 때문이다.<br/>
 - <오늘의 온도> -> <이달의 랭킹><br/>
 - <베팅> : 오늘의 온도에 베팅<br/>
 - <감정 기록> : 이번 달에 내가 선택한 온도, 요소<br/>

## 기술
### 아키텍처
MVVM, domain

### 언어
kotlin

### 라이브러리
```
plugins {
    // BindingAdapter
    id 'kotlin-kapt'
}
dependencies {
    // retrofit2 의존성
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // junit 의존성
    androidTestImplementation('androidx.test:runner:1.3.0')
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")

    // viewModel
    implementation "androidx.fragment:fragment-ktx:1.3.2"
}
```
