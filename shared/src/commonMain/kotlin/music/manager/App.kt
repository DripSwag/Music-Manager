package music.manager

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import io.github.vinceglb.filekit.FileKit

import music.manager.ui.Landing
import music.manager.ui.modal.OutputDirectoryErrorModal
import music.manager.ui.modal.SettingsModal
import music.manager.viewmodels.ErrorViewModel
import music.manager.viewmodels.SettingsViewModel

@Composable
@Preview
fun App(
    settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() },
    errorViewModel: ErrorViewModel = viewModel { ErrorViewModel() }
) {
    val settingsState by settingsViewModel.settingsState.collectAsState()
    val errorState by errorViewModel.errorState.collectAsState()

    onLoad()

    AppTheme(darkTheme = true) {
        Landing()

        when {
            settingsState.modalVisible -> SettingsModal()
            errorState.outputDirectory -> OutputDirectoryErrorModal()
        }
    }
}

private fun onLoad() {
    FileKit.init("music.manager")
}