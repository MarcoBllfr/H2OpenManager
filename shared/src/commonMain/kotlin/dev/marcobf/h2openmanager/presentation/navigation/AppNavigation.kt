package dev.marcobf.h2openmanager.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumFormScreen
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListScreen
import dev.marcobf.h2openmanager.presentation.device.DeviceScreen
import dev.marcobf.h2openmanager.presentation.home.HomeScreen
import dev.marcobf.h2openmanager.presentation.settings.SettingsScreen

enum class Tab(val label: String, val icon: String) {
    HOME("Home", "🏠"),
    LIST("Lista", "📋"),
    DEVICE("Device", "📡"),
    SETTINGS("Impostazioni", "⚙️")
}

@Composable
fun AppNavigation() {
    var selectedTab by remember { mutableStateOf(Tab.HOME) }
    var showForm by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                Tab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = { Text(tab.icon) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                Tab.HOME -> HomeScreen()
                Tab.LIST -> {
                    if (showForm) {
                        AquariumFormScreen(onBack = { showForm = false })
                    } else {
                        AquariumListScreen(onAddClick = { showForm = true })
                    }
                }
                Tab.DEVICE -> DeviceScreen()
                Tab.SETTINGS -> SettingsScreen()
            }
        }
    }
}
