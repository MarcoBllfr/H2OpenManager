package dev.marcobf.h2openmanager

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumFormScreen
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showForm by remember { mutableStateOf(false) }
        if (showForm) {
            AquariumFormScreen(onBack = { showForm = false })
        } else {
            AquariumListScreen(onAddClick = { showForm = true })
        }
    }
}