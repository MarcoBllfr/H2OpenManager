package dev.marcobf.h2openmanager.domain.model

enum class TaskType(val label: String){
    WATER_CHANGE("💧 Cambio acqua"),
    FILTER_CLEAN("🧽 Pulizia filtro"),
    FERTILIZE("🌱 Fertilizzante"),
    FEED("🦐 Alimentazione"),
    GENERAL("🛠️ Generale")
}