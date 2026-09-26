package dev.marcobf.h2openmanager.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getLongExtra(Extra.TASK_ID, -1L)
        val aquariumName = intent.getStringExtra(Extra.AQUARIUM_NAME) ?: ""
        val taskLabel = intent.getStringExtra(Extra.TASK_LABEL) ?: ""

        val text = if (taskLabel.isBlank()) aquariumName else "$aquariumName: $taskLabel"

        val notification = NotificationCompat.Builder(context, Extra.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
        .setContentTitle(aquariumName)
        .setContentText(taskLabel)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true) //clear notify frame after click
            .build()

        NotificationManagerCompat.from(context).notify(taskId.toInt(), notification)
    }
}
