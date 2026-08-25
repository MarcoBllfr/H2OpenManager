package dev.marcobf.h2openmanager.presentation.aquarium

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.model.WaterType
import org.koin.compose.koinInject
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun AquariumFormScreen(
    viewModel: AquariumListViewModel = koinInject(),
    onBack: () -> Unit,
    aquarium: Aquarium?
) {
    var name by remember { mutableStateOf(aquarium?.name ?: "")  }
    var liters by remember { mutableStateOf(aquarium?.liters?.toString() ?: "") }
    var waterType by remember { mutableStateOf(aquarium?.type ?: WaterType.FRESHWATER) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Nuovo Acquario", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Tipo acquario", style = MaterialTheme.typography.labelLarge)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = waterType == WaterType.FRESHWATER,
                    onClick = { waterType = WaterType.FRESHWATER }
                )
                Text("Dolce")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = waterType == WaterType.SALTWATER,
                    onClick = { waterType = WaterType.SALTWATER }
                )
                Text("Salata")
            }
        }

        OutlinedTextField(
            value = liters,
            onValueChange = { liters = it },
            label = { Text("Litri") },
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
                    val litersValue = liters.toDoubleOrNull()
                    if (name.isNotBlank() && litersValue != null) {
                        if (aquarium != null) {
                            viewModel.updateAquarium(aquarium.copy(
                                name = name,
                                liters = litersValue,
                                type = waterType
                            ))
                        } else {
                            viewModel.insertAquarium(
                                Aquarium(name = name, liters = litersValue, type = waterType)
                            )
                        }
                        onBack()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salva")
            }

        }
    }
}
