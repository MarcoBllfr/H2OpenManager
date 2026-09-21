package dev.marcobf.h2openmanager.presentation.maintenance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.MaintenanceTask
import dev.marcobf.h2openmanager.domain.model.TaskType
import dev.marcobf.h2openmanager.domain.utils.addDays
import dev.marcobf.h2openmanager.domain.utils.todayEpochDays
import org.koin.compose.koinInject

@Composable
fun MaintenanceFormScreen(
    aquariumId: Long,
    viewModel: MaintenanceViewModel = koinInject(),
    task: MaintenanceTask? = null,
    onBack: () -> Unit = {},
    onSaved: () -> Unit = {}
) {
    var type by remember { mutableStateOf(task?.type ?: TaskType.WATER_CHANGE) }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var daysFromToday by remember {
        mutableStateOf((task?.dueDate?.minus(todayEpochDays())?.toInt() ?: 7).toString())
    }
    var intervalDays by remember { mutableStateOf(task?.intervalDays?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            if (task != null) "Modifica Task" else "Nuovo Task",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(8.dp))

        Text("Tipo", style = MaterialTheme.typography.labelLarge)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TaskType.entries.forEach { t ->
                FilterChip(
                    selected = type == t,
                    onClick = { type = t },
                    label = { Text(t.label) }
                )
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrizione (opzionale)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = daysFromToday,
            onValueChange = { daysFromToday = it },
            label = { Text("Tra quanti giorni (scadenza)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = intervalDays,
            onValueChange = { intervalDays = it },
            label = { Text("Ricorrenza ogni N giorni (vuoto = una volta)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {
                Text("Annulla")
            }
            Button(
                onClick = {
                    val days = daysFromToday.toIntOrNull()
                    if (days != null && days >= 0) {
                        val dueDate = addDays(todayEpochDays(), days)
                        val interval = intervalDays.toIntOrNull()
                        if (task != null) {
                            viewModel.updateTask(
                                task.copy(
                                    type = type,
                                    description = description,
                                    dueDate = dueDate,
                                    intervalDays = interval
                                )
                            )
                        } else {
                            viewModel.insertTask(
                                MaintenanceTask(
                                    aquariumId = aquariumId,
                                    type = type,
                                    description = description,
                                    dueDate = dueDate,
                                    intervalDays = interval
                                )
                            )
                        }
                        onSaved()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salva")
            }
        }

        if (task != null) {
            OutlinedButton(
                onClick = {
                    viewModel.deleteTask(task)
                    onSaved()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Elimina task")
            }
        }
    }
}