package dev.marcobf.h2openmanager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.marcobf.h2openmanager.domain.model.WaterType

@Entity(tableName = "aquariums")
data class AquariumEntity(
@PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val name: String,
    val type: WaterType,
    val liters: Double,
    val createdAt: Long = 0,
)
