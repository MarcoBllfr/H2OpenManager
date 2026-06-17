package dev.marcobf.h2openmanager.presentation.aquarium

import androidx.lifecycle.ViewModel
import dev.marcobf.h2openmanager.domain.repository.AquariumRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AquariumListViewModel (
    private val aquariumRepository: AquariumRepository,
): ViewModel(){
    private val _uiState = MutableStateFlow(AquariumListUiState())
    val uiState : StateFlow<AquariumListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch{
            aquariumRepository.getAllAquariums()
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { _uiState.update { it.copy(isLoading = false) }}
                .collect { aquariums -> _uiState.update { it.copy(aquariums = aquariums, isLoading = false) } }
        }
    }
}