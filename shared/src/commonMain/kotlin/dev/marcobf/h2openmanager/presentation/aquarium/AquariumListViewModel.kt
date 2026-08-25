package dev.marcobf.h2openmanager.presentation.aquarium

import androidx.lifecycle.ViewModel
import dev.marcobf.h2openmanager.domain.repository.AquariumRepository
import androidx.lifecycle.viewModelScope
import dev.marcobf.h2openmanager.domain.model.Aquarium
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AquariumListViewModel (
    private val aquariumRepository: AquariumRepository,
): ViewModel(){
    private val _uiState = MutableStateFlow(AquariumListUiState())
    val uiState : StateFlow<AquariumListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch{
            _uiState.update { it.copy(isLoading = true) }
            aquariumRepository.getAllAquariums()
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { aquariums -> _uiState.update { it.copy(aquariums = aquariums, isLoading = false) }}
        }
    }
    fun deleteAquarium(aquarium: Aquarium){
        viewModelScope.launch { aquariumRepository.deleteAquarium(aquarium) }
    }

    fun insertAquarium(aquarium: Aquarium){
        viewModelScope.launch { aquariumRepository.insertAquarium(aquarium) }
    }

    fun setFavorite(id: Long) {
        viewModelScope.launch { aquariumRepository.setFavorite(id) }
    }

    fun updateAquarium(aquarium: Aquarium) {
        viewModelScope.launch { aquariumRepository.updateAquarium(aquarium) }
    }

}