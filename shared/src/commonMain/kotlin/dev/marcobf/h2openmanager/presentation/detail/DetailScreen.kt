package dev.marcobf.h2openmanager.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.model.WaterType
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListViewModel
import dev.marcobf.h2openmanager.presentation.maintenance.MaintenanceViewModel
import dev.marcobf.h2openmanager.presentation.maintenance.TaskRow
import org.koin.compose.koinInject

@Composable
fun DetailScreen(
    aquariumId : Long,
    viewModel: AquariumListViewModel= koinInject(),
    maintenanceViewModel: MaintenanceViewModel = koinInject(),
    onBack: () -> Unit = {},
    onEdit: (Aquarium) -> Unit = {},
    onAddTask:(aquariumId: Long) -> Unit= {}
){
    val state by viewModel.uiState.collectAsState()
    val aquarium = state.aquariums.find { it.id == aquariumId }
    val maintenanceState by maintenanceViewModel.uiState.collectAsState()
    LaunchedEffect(aquariumId) { maintenanceViewModel.loadTasks(aquariumId) }

    when(aquarium){
        null ->{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = "Acquario non trovato")
            }
        }
        else ->{
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TextButton(onClick = onBack){Text (text = "Indietro")}
                    TextButton(onClick = { onEdit(aquarium) }) {
                        Text("Modifica")
                    }
                    TextButton(
                        onClick = {
                            viewModel.deleteAquarium(aquarium)
                            onBack()
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Elimina")
                    }
                }
                Spacer(Modifier.height(16.dp))

                Text(text = aquarium.name, style = MaterialTheme.typography.headlineLarge)

                Spacer(Modifier.height(8.dp))

                Text(
                    "${if(aquarium.type == WaterType.FRESHWATER)"Acqua Dolce " else "Acqua Salata"} ${aquarium.liters}L"
                )

                if(aquarium.isFavorite){
                    Text("★ Preferito", style = MaterialTheme.typography.labelLarge)
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Manutenzione", style = MaterialTheme.typography.titleLarge)
                    TextButton(onClick = { onAddTask(aquarium.id) }) {
                        Text("＋ Aggiungi")
                    }
                }

                when {
                    maintenanceState.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    maintenanceState.tasks.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Nessun task di manutenzione")
                        }
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                items = maintenanceState.tasks,
                                key = { it.id }
                            ) { task ->
                                TaskRow(
                                    task = task,
                                    onClick = { maintenanceViewModel.toggleCompleted(task) },
                                    onDelete = { maintenanceViewModel.deleteTask(task) }
                                )
                            }
                        }
                    }
                }
                //more parameters and info in future
            }
        }

    }
}