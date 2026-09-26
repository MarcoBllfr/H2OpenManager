package dev.marcobf.h2openmanager.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import dev.marcobf.h2openmanager.domain.notification.NotificationScheduler

class AndroidNotificationScheduler(
    private val context: Context
) : NotificationScheduler {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    init {
        createNotificationChannel()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Extra.CHANNEL_ID, "Manutenzione", NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun scheduleTaskReminder(taskId: Long, aquariumName: String,
                                      taskLabel: String, dueDate: Long, notifyBeforeDays: Int) {
        val triggerAtMillis = (dueDate - notifyBeforeDays) * 86_400_000L

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(Extra.TASK_ID, taskId)
            putExtra(Extra.AQUARIUM_NAME, aquariumName)
            putExtra(Extra.TASK_LABEL, taskLabel)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, taskId.toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
    }
    override fun cancelTaskReminder(taskId: Long) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, taskId.toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

}