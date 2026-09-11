package dev.marcobf.h2openmanager.data.repository

import dev.marcobf.h2openmanager.data.local.dao.MaintenanceDao
import dev.marcobf.h2openmanager.data.mapper.toDomain
import dev.marcobf.h2openmanager.data.mapper.toEntity
import dev.marcobf.h2openmanager.domain.model.MaintenanceTask
import dev.marcobf.h2openmanager.domain.repository.MaintenanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MaintenanceRepositoryImpl(
    private val maintenanceDao: MaintenanceDao
) : MaintenanceRepository {

    override fun getTasksByAquarium(aquariumId: Long): Flow<List<MaintenanceTask>> {
        return maintenanceDao.getTasksByAquarium(aquariumId).map { entities -> entities.map{it.toDomain()} }
    }

    override suspend fun insertTask(task: MaintenanceTask): Long {
        return maintenanceDao.insertTask(task.toEntity())
    }

    override suspend fun updateTask(task: MaintenanceTask) {
       return maintenanceDao.updateTask(task.toEntity())
    }

    override suspend fun deleteTask(task: MaintenanceTask) {
        return maintenanceDao.deleteTask(task.toEntity())
    }
}