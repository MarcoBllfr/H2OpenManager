package dev.marcobf.h2openmanager.data.mapper

import dev.marcobf.h2openmanager.data.local.entity.MaintenanceEntity
import dev.marcobf.h2openmanager.domain.model.MaintenanceTask
import dev.marcobf.h2openmanager.domain.model.TaskType

fun MaintenanceEntity.toDomain(): MaintenanceTask = MaintenanceTask(
    id = id,
    aquariumId = aquariumId,
    type = TaskType.valueOf(type.name),
    description = description,
    dueDate = dueDate,
    intervalDays = intervalDays,
    isCompleted = isCompleted,
    notifiedBeforeDays = notifiedBeforeDays
)

fun MaintenanceTask.toEntity(): MaintenanceEntity = MaintenanceEntity(
    id = id,
    aquariumId = aquariumId,
    type = type,
    description = description,
    dueDate = dueDate,
    intervalDays = intervalDays,
    isCompleted = isCompleted,
    notifiedBeforeDays = notifiedBeforeDays
)