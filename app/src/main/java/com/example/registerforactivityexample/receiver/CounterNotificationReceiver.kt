package com.example.registerforactivityexample.receiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import com.example.registerforactivityexample.Counter
import com.example.registerforactivityexample.service.CounterNotificationService

class CounterNotificationReceiver: BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent?) {
        val service = CounterNotificationService(context)
        service.showNotification(++Counter.value)
    }
}