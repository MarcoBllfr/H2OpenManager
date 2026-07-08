package dev.marcobf.h2openmanager.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("⚙️ Impostazioni", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Text("Lingua: Italiano", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(8.dp))
        Text("Unità volume: Litri", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(8.dp))
        Text("Temperatura: °C", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(24.dp))
        Divider()
        Spacer(Modifier.height(16.dp))
        Text("📱 Versione 1.0.0", style = MaterialTheme.typography.bodySmall)
        Text("ℹ️ H2OpenManager", style = MaterialTheme.typography.bodySmall)
    }
}
