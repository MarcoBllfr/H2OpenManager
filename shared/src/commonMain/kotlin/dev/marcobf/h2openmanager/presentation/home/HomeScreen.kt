package dev.marcobf.h2openmanager.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: AquariumListViewModel = koinInject(),
    onCreateClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    val favorite = state.aquariums.find { it.isFavorite }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                Spacer(Modifier.height(16.dp))
                Text(
                    "Crea il tuo primo acquario",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(24.dp))
                Button(onClick = onCreateClick) {
                    Text("＋ Crea acquario")
                }
            }
        }
    }
}