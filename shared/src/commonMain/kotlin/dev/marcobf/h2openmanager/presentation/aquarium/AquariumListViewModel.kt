package dev.marcobf.h2openmanager.presentation.aquarium

import androidx.lifecycle.ViewModel
import dev.marcobf.h2openmanager.domain.repository.AquariumRepository
import androidx.lifecycle.viewModelScope
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.model.WaterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


class AquariumListViewModel (
    private val aquariumRepository: AquariumRepository,
): ViewModel(){
    private val _uiState = MutableStateFlow(AquariumListUiState())
    val uiState : StateFlow<AquariumListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch{
            aquariumRepository.getAllAquariums()
                .onStart { _uiState.update { it.copy(isLoading = true) }  }
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { aquariums -> _uiState.update { it.copy(aquariums = aquariums, isLoading = false) }}
        }
    }
    fun deleteAquarium(aquarium: Aquarium){
        viewModelScope.launch { aquariumRepository.deleteAquarium(aquarium) }
    }

    fun insertTestAquarium(){
        viewModelScope.launch {
            aquariumRepository.insertAquarium(
                Aquarium(
                    name = "Test",
                    liters = 100.00,
                    type = WaterType.FRESHWATER
                )
        )
        }
    }
}