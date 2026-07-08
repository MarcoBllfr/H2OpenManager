package dev.marcobf.h2openmanager.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.Aquarium
import dev.marcobf.h2openmanager.domain.repository.AquariumRepository
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: AquariumListViewModel = koinInject()
) {
    val state by viewModel.uiState.collectAsState()
    val favorite = state.aquariums.find { it.isFavorite }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (favorite != null) {
            Text(
                "🏠 ${favorite.name}",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "${if (favorite.type.name == "FRESHWATER") "💧" else "🌊"} ${favorite.liters}L",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))
            Text("★ Preferito", style = MaterialTheme.typography.labelLarge)
        } else {
            Text(
                "🐠 Home",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Nessun acquario preferito",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                "Aggiungine uno dalla sezione Lista",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
