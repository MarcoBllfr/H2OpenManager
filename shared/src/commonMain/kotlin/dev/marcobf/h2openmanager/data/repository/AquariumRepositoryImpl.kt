package dev.marcobf.h2openmanager.data.repository

import dev.marcobf.h2openmanager.data.local.dao.AquariumDao
import dev.marcobf.h2openmanager.data.mapper.toDomain
import dev.marcobf.h2openmanager.data.mapper.toEntity
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.repository.AquariumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AquariumRepositoryImpl(
    private val aquariumDao: AquariumDao
) : AquariumRepository {
    override fun getAllAquariums(): Flow<List<Aquarium>> {
        return aquariumDao.getAllAquariums().map { entities -> entities.map{it.toDomain()} }
    }

    override suspend fun getAquariumById(id: Long): Aquarium? {
      return aquariumDao.getAquariumById(id)?.toDomain()
    }

    override suspend fun insertAquarium(aquarium: Aquarium): Long {
        return aquariumDao.insertAquarium(aquarium.toEntity())
    }

    override suspend fun updateAquarium(aquarium: Aquarium) {
        aquariumDao.updateAquarium(aquarium.toEntity())
    }

    override suspend fun deleteAquarium(aquarium: Aquarium) {
        aquariumDao.deleteAquarium(aquarium.toEntity())
    }
}