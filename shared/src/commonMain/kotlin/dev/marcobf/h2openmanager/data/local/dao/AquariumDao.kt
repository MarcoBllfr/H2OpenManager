package dev.marcobf.h2openmanager.data.local.dao

import androidx.room.*
import dev.marcobf.h2openmanager.data.local.entity.AquariumEntity
import dev.marcobf.h2openmanager.domain.model.Aquarium
import kotlinx.coroutines.flow.Flow

@Dao
interface AquariumDao {

    @Query("SELECT * FROM aquariums ORDER BY createdAt DESC")
    fun getAllAquariums(): Flow<List<AquariumEntity>>

    @Query("SELECT * FROM aquariums WHERE id = :id")
    suspend fun getAquariumById(id: Long): AquariumEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAquarium(aquarium: AquariumEntity): Long

    @Update
    suspend fun updateAquarium(aquarium: AquariumEntity)

    @Delete
    suspend fun deleteAquarium(aquarium: AquariumEntity)
}