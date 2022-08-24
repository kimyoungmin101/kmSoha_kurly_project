package com.example.kurly_project_app


import android.app.Application
import com.example.kurly_project_app.util.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GsohaApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
// 데이터 조회
//    MyApplication.prefs.getString("email", "no email")
//
// 데이터 저장
//    MyApplication.prefs.setString("email", "abcd@gmail.com")

}