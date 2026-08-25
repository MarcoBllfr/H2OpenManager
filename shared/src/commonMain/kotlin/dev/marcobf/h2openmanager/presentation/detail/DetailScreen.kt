package dev.marcobf.h2openmanager.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListViewModel
import org.koin.compose.koinInject
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.model.WaterType

@Composable
fun DetailScreen(
    aquariumId : Long,
    viewModel: AquariumListViewModel= koinInject(),
    onBack: () -> Unit = {},
    onEdit: (Aquarium) -> Unit = {}
){
    val state by viewModel.uiState.collectAsState()
    val aquarium = state.aquariums.find { it.id == aquariumId }

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
                //more parameters and info in future
            }
        }

    }
}