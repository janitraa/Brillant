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

    private fun getAudioNotification(): Notification {
        val contentIntent = PendingIntent.getActivity(
            ctx,
            0,
            Intent(), // add this
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(ctx, "10001")
            .setContentTitle("Brillant is Recording")
            .setContentText("-")
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(false)
            .setProgress(100, 0, true)
            .setLights(Color.BLUE, 100, 100)
            .setChannelId(MainActivity.NOTIFICATION_CHANNEL_ID)

        return builder.build()
    }
}