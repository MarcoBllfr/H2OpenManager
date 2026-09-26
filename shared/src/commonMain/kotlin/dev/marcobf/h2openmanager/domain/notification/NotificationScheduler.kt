package dev.marcobf.h2openmanager.domain.notification

interface NotificationScheduler {
    fun scheduleTaskReminder(
        taskId: Long,
        aquariumName: String,
        taskLabel: String,
        dueDate: Long,
        notifyBeforeDays: Int
    )
    fun cancelTaskReminder(taskId: Long)
}