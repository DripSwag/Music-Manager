package music.manager.ui.btn

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.lib.PropertyHelpers
import music.manager.viewmodels.SettingsViewModel


@Composable
@Preview
fun SelectSongsOutputBTN(settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }) {
    val outputSongsLauncher = rememberDirectoryPickerLauncher { file ->
        settingsViewModel.setOutputDirectory(file?.path ?: "")
    }

    Button(
        onClick = { outputSongsLauncher.launch() }
    ) {
        Text("Pick Output Directory")
    }
}