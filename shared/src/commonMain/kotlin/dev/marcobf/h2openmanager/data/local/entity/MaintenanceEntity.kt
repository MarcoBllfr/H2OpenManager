package dev.marcobf.h2openmanager.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.marcobf.h2openmanager.domain.model.TaskType

@Entity(
    tableName = "maintenance_tasks",
    foreignKeys = [
        ForeignKey(
            entity = AquariumEntity::class,
            parentColumns = ["id"],
            childColumns = ["aquariumId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("aquariumId")]
)
data class MaintenanceEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val aquariumId: Long,
    val type: TaskType,
    val description: String = "",
    val dueDate: Long,
    val intervalDays: Int? = null,
    val isCompleted: Boolean = false,
    val notifiedBeforeDays: Int = 1

)