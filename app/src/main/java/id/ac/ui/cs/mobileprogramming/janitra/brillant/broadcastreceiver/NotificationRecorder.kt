package id.ac.ui.cs.mobileprogramming.janitra.brillant.broadcastreceiver

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.janitra.brillant.MainActivity
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import id.ac.ui.cs.mobileprogramming.janitra.brillant.ui.HomepageFragment
import java.util.*

class NotificationRecorder (val ctx: Context) {

    companion object {
        val ACTION_STOP_RECORDER = "ACTION_STOP_RECORDER"
    }

    fun initAudioNotification() {
        nonSchedulNotification(NotificationPublisher.NOTIFICATION_AUDIO_ID, getAudioNotification())
    }

    fun cancelAudioNotification() {
        val ns = Context.NOTIFICATION_SERVICE
        val notifManager = ctx.getSystemService(ns) as NotificationManager
        notifManager.cancel(NotificationPublisher.NOTIFICATION_AUDIO_ID)
    }

    private fun nonSchedulNotification(notifID: Int, notification: Notification) {
        with(NotificationManagerCompat.from(ctx)) {
            notify(notifID, notification)
        }
    }

    private fun scheduleNotification(notification: Notification) {
        val notificationIntent = Intent(ctx, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            ctx,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmStartTime = Calendar.getInstance()
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 8)
        alarmStartTime.set(Calendar.MINUTE, 0)
        alarmStartTime.set(Calendar.SECOND, 0)

        val alarmManager = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    private fun getAudioNotification(): Notification {
//        val resultIntent = Intent(ctx, MainActivity::class.java).apply {
//            action = ACTION_STOP_RECORDER
//            putExtra("action", ACTION_STOP_RECORDER)
//        }

        val contentIntent = PendingIntent.getActivity(
            ctx,
            0,
            Intent(), // add this
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(ctx)
            .setContentTitle("PersonalEnglish is Recording")
            .setContentText("-")
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(false)
            .setProgress(100, 0, true)
            .setLights(Color.BLUE, 100, 100)
            .setChannelId(MainActivity.NOTIFICATION_CHANNEL_ID)

        return builder.build()
    }

    private fun notificationAction(actionNumber: Int): NotificationCompat.Action? {
        var actionCourier = Intent(ctx, HomepageFragment::class.java)

        when (actionNumber) {
            0 -> {
                actionCourier.putExtra("action", ACTION_STOP_RECORDER)

                val contentIntent = PendingIntent.getActivity(
                    ctx,
                    0,
                    actionCourier, // add this
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                val action = NotificationCompat.Action.Builder(
                    R.drawable.ic_noun_deadline,
                    "Stop",
                    contentIntent
                ).build()

                return action
            }

            else -> {
                return null
            }
        }
    }
}