package dev.marcobf.h2openmanager.presentation.maintenance

import dev.marcobf.h2openmanager.domain.model.MaintenanceTask

data class MaintenanceUiState(
    val tasks: List<MaintenanceTask> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,

)
