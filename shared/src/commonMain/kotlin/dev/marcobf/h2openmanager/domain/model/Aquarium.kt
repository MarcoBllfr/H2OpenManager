package dev.marcobf.h2openmanager.domain.model

enum class WaterType{
    FRESHWATER,
    SALTWATER
}
data class Aquarium(
    val id: Long = 0,
    val name: String,
    val type: WaterType,
    val liters: Double,
    val createdAt: Long = 0,

)
