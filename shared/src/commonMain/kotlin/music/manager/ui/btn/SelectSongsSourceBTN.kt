package music.manager.ui.btn

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.lib.PropertyHelpers
import music.manager.viewmodels.SettingsViewModel


@Composable
@Preview
fun SelectSongsSourceBTN(settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }) {
    val sourceSongsLauncher = rememberDirectoryPickerLauncher { file ->
        settingsViewModel.setSourceDirectory(file?.path ?: "")
    }

    Button(
        onClick = { sourceSongsLauncher.launch() }
    ) {
        Text("Pick Source Directory")
    }
}