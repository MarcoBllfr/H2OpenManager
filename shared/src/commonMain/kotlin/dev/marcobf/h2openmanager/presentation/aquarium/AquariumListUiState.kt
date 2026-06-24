package dev.marcobf.h2openmanager.presentation.aquarium

import dev.marcobf.h2openmanager.domain.model.Aquarium

data class AquariumListUiState(
    val aquariums: List<Aquarium> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
