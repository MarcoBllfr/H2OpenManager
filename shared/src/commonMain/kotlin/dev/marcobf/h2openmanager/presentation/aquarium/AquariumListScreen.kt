package dev.marcobf.h2openmanager.presentation.aquarium

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.WaterType
import org.koin.compose.koinInject

@Composable
fun AquariumListScreen(
 onAddClick: () -> Unit = {},
 viewModel: AquariumListViewModel = koinInject()
) {
 val state by viewModel.uiState.collectAsState()
 Scaffold(
  floatingActionButton = {
   FloatingActionButton(onClick = onAddClick) {
    Text("+", style = MaterialTheme.typography.headlineMedium)
   }
  }
 ) { padding ->
  when {
   state.isLoading -> {
    Box(
     contentAlignment = Alignment.Center,
     modifier = Modifier.fillMaxSize()
    ) {
     CircularProgressIndicator(modifier = Modifier.size(32.dp))
    }
   }
   state.error != null -> {
    Box(
     contentAlignment = Alignment.Center,
     modifier = Modifier.fillMaxSize()
    ) {
     Text("Errore: ${state.error}")
    }
   }
   state.aquariums.isEmpty() -> {
    Box(
     contentAlignment = Alignment.Center,
     modifier = Modifier.fillMaxSize()
    ) {
     Text("Nessun acquario")
    }
   }
   else -> {
    LazyColumn(
     modifier = Modifier
      .fillMaxSize()
      .padding(padding),
     contentPadding = PaddingValues(16.dp),
     verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
     items(state.aquariums, key = { it.id }) { aquarium ->
      Card(
       modifier = Modifier.fillMaxWidth()
      ) {
        Row(
         modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
         horizontalArrangement = Arrangement.SpaceBetween,
         verticalAlignment = Alignment.CenterVertically
        ) {
         Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f)
         ) {
          Text(
           if (aquarium.isFavorite) "⭐" else "☆",
           modifier = Modifier.clickable { viewModel.setFavorite(aquarium.id) }
          )
          Spacer(Modifier.width(8.dp))
          Column {
           Text(aquarium.name, style = MaterialTheme.typography.titleMedium)
           Text(
            "${if (aquarium.type == WaterType.FRESHWATER) "💧 Acqua dolce" else "🌊 Acqua salata"} · ${aquarium.liters}L",
            style = MaterialTheme.typography.bodySmall
           )
          }
         }
         IconButton(onClick = { viewModel.deleteAquarium(aquarium) }) {
          Text("✕", color = MaterialTheme.colorScheme.error)
         }
        }
      }
     }
    }
   }
  }
 }
}