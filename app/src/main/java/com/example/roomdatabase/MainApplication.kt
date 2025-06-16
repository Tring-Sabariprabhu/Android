package com.example.roomdatabase


import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("MyTag", "Created Application with Hilt")
    }
}