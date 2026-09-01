package music.manager

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import io.github.vinceglb.filekit.FileKit

import music.manager.ui.Landing
import music.manager.ui.modal.SettingsModal
import music.manager.viewmodels.SettingsViewModel

@Composable
@Preview
fun App(settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }) {
    val settingsState by settingsViewModel.settingsState.collectAsState()

    onLoad()

    AppTheme(darkTheme = true) {
        Landing()

        when {
            settingsState.modalVisible -> SettingsModal()
        }
    }
}

private fun onLoad() {
    FileKit.init("music.manager")
}