package dev.marcobf.h2openmanager.data.mapper

import dev.marcobf.h2openmanager.data.local.entity.AquariumEntity
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.model.WaterType

fun AquariumEntity.toDomain(): Aquarium = Aquarium(
    id = id,
    name = name,
    type = WaterType.valueOf(type.name),
    liters = liters,
    isFavorite = isFavorite,
    createdAt = createdAt
)

fun Aquarium.toEntity(): AquariumEntity = AquariumEntity(
    id = id,
    name = name,
    type = type,
    liters = liters,
    isFavorite = isFavorite,
    createdAt = createdAt
)