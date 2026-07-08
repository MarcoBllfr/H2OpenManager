package dev.marcobf.h2openmanager

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import dev.marcobf.h2openmanager.presentation.navigation.AppNavigation

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}
