package dev.marcobf.h2openmanager.domain.repository

import dev.marcobf.h2openmanager.data.local.entity.MaintenanceEntity
import dev.marcobf.h2openmanager.domain.model.MaintenanceTask
import kotlinx.coroutines.flow.Flow

interface MaintenanceRepository {
    fun getTasksByAquarium(aquariumId: Long): Flow<List<MaintenanceTask>>
    suspend fun insertTask(task: MaintenanceTask): Long
    suspend fun updateTask(task: MaintenanceTask)
    suspend fun deleteTask(task: MaintenanceTask)
}