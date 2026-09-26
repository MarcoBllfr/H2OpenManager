package dev.marcobf.h2openmanager.presentation.maintenance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcobf.h2openmanager.domain.model.MaintenanceTask
import dev.marcobf.h2openmanager.domain.repository.MaintenanceRepository
import dev.marcobf.h2openmanager.domain.utils.addDays
import dev.marcobf.h2openmanager.domain.utils.todayEpochDays
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MaintenanceViewModel(
    private val maintenanceRepository: MaintenanceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MaintenanceUiState())
    val uiState : StateFlow<MaintenanceUiState> = _uiState.asStateFlow()



    fun loadTasks(aquariumId: Long){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
                maintenanceRepository.getTasksByAquarium(aquariumId)
                    .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                    .collect { maintenanceTasks -> _uiState.update { it.copy(tasks = maintenanceTasks,isLoading = false) }  }

            }
        }
    fun insertTask(task: MaintenanceTask) {
        viewModelScope.launch { maintenanceRepository.insertTask(task) }
    }

    fun updateTask(task: MaintenanceTask) {
        viewModelScope.launch { maintenanceRepository.updateTask(task) }
    }

    fun deleteTask(task: MaintenanceTask) {
        viewModelScope.launch { maintenanceRepository.deleteTask(task) }
    }

    fun toggleCompleted(task: MaintenanceTask) {
        viewModelScope.launch {
            val next =
                if (task.isCompleted) {
                    task.copy(isCompleted = false)
                } else if (task.intervalDays != null) {
                    val newDate = addDays(todayEpochDays(), task.intervalDays)
                    task.copy(isCompleted = true, dueDate = newDate)
                } else {
                    task.copy(isCompleted = true)
                }
            maintenanceRepository.updateTask(next)
        }
    }


}