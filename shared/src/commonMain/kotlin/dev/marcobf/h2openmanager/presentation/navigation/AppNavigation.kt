package dev.marcobf.h2openmanager.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumFormScreen
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListScreen
import dev.marcobf.h2openmanager.presentation.detail.DetailScreen
import dev.marcobf.h2openmanager.presentation.device.DeviceScreen
import dev.marcobf.h2openmanager.presentation.home.HomeScreen
import dev.marcobf.h2openmanager.presentation.settings.SettingsScreen
import dev.marcobf.h2openmanager.domain.model.Aquarium


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
    var selectedAquariumId by remember { mutableStateOf<Long?>(null) }
    var editingAquarium by remember { mutableStateOf<Aquarium?>(null) }


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
                    when {
                        editingAquarium != null -> AquariumFormScreen(
                            onBack = { editingAquarium = null },
                            aquarium = editingAquarium
                        )
                        selectedAquariumId != null -> DetailScreen(
                            aquariumId = selectedAquariumId!!,
                            onBack = { selectedAquariumId = null },
                            onEdit = { editingAquarium = it }
                        )
                        showForm -> AquariumFormScreen(onBack = { showForm = false }, aquarium = null)
                        else -> AquariumListScreen(
                            onAddClick = { showForm = true },
                            onAquariumClick = { id -> selectedAquariumId = id }
                        )
                    }
                }
                Tab.DEVICE -> DeviceScreen()
                Tab.SETTINGS -> SettingsScreen()
            }
        }
    }
}
