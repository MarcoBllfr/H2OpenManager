package dev.marcobf.h2openmanager.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.marcobf.h2openmanager.data.local.entity.MaintenanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MaintenanceDao {
    @Query("SELECT * FROM maintenance_tasks WHERE aquariumId = :aquariumId ORDER BY dueDate ASC")
    fun getTasksByAquarium(aquariumId: Long): Flow<List<MaintenanceEntity>>
    @Insert
    suspend fun insertTask(task: MaintenanceEntity): Long
    @Update
    suspend fun updateTask(task: MaintenanceEntity)
    @Delete
    suspend fun deleteTask(task: MaintenanceEntity)
}