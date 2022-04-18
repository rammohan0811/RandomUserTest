package com.example.mvvmdemo.base

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController : Application() {
    override fun onCreate() {
        Log.d("test","test")
        super.onCreate()
    }
}