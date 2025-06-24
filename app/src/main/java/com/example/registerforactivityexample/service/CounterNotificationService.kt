package com.example.registerforactivityexample.service

import android.Manifest
import android.R
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.registerforactivityexample.receiver.CounterNotificationReceiver
import com.example.registerforactivityexample.activity.MainActivity

class CounterNotificationService (
    val context: Context
) {
    companion object {
        val channelId = "counter_channel"
    }
    val incrementIntent = PendingIntent.getBroadcast(
        context,
        2,
        Intent(context, CounterNotificationReceiver::class.java),
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
    )
    val intent = PendingIntent.getActivity(context,1, Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE)

@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
fun showNotification(value: Int){
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.btn_star)
            .setContentTitle("Counter Notification")
            .setContentText("Count : $value")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(
                R.drawable.btn_plus,
                "Increment",
                incrementIntent
            )
            .setContentIntent(intent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1001, builder.build())
        Log.i("IN_APP", "Notification created")
    }
}