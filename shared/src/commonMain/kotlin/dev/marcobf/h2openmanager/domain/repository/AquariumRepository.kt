package dev.marcobf.h2openmanager.domain.repository


import dev.marcobf.h2openmanager.domain.model.Aquarium
import kotlinx.coroutines.flow.Flow

interface AquariumRepository {
    fun getAllAquariums(): Flow<List<Aquarium>>
    suspend fun getAquariumById(id: Long): Aquarium?
    suspend fun insertAquarium(aquarium: Aquarium): Long
    suspend fun updateAquarium(aquarium: Aquarium)
    suspend fun deleteAquarium(aquarium: Aquarium)
}