package dev.marcobf.h2openmanager.domain.model



data class MaintenanceTask(
    val id: Long = 0,
    val aquariumId: Long,
    val type: TaskType,
    val description: String = "",
    val dueDate: Long,
    val intervalDays: Int? = null,
    val isCompleted: Boolean = false,
    val notifiedBeforeDays: Int = 1
)