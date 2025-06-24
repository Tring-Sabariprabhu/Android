package com.example.registerforactivityexample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.example.registerforactivityexample.service.CounterNotificationService


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CounterNotificationService.channelId,
                "Increment",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "This channel is used for Counter "

            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
            Log.i("IN_APP","Notification channel created")
        }
    }
}