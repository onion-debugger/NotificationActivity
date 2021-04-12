package com.decagon.mubarak.noficationactivity


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val channelId = "Some id"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notify_button.setOnClickListener {
            showNotification()
        }
    }

    fun showNotification() {
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.channel_description),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = getString(R.string.channel_description)
            mNotificationManager.createNotificationChannel(channel)
        }
        val mBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // notification icon
            .setContentTitle(getString(R.string.notification_title)) // title for notification
            .setContentText(getString(R.string.notification_message)) // message for notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // clear notification after click
        val intent = Intent(this, NotificationPageActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(pi)
        mNotificationManager.notify(0, mBuilder.build())
    }
}