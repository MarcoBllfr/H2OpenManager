package dev.marcobf.h2openmanager.presentation.device

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeviceScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("📡", style = MaterialTheme.typography.displayLarge)
            Spacer(Modifier.height(8.dp))
            Text("Dispositivi IoT", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(4.dp))
            Text("Nessun dispositivo connesso", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
