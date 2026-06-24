package dev.marcobf.h2openmanager.presentation.aquarium

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun AquariumListScreen(
 viewModel: AquariumListViewModel = koinInject()
){
  val state by viewModel.uiState.collectAsState()
 when{
  state.isLoading -> {
   Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
   ){
    CircularProgressIndicator(modifier = Modifier.size(32.dp))
   }
  }
  state.error != null -> {
   Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
   ){
    Text(text = "Error: (${state.error})")
   }
  }

  state.aquariums.isEmpty() -> {
   Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
   ){
    Text(text = "No aquariums for now")
   }
  }else -> {
   //creare una LazyColumn per la lista degli acquari
   Text(text = "List aquariums (${state.aquariums.size})")
  }

 }
}
