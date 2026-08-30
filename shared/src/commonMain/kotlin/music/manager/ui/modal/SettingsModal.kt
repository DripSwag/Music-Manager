package music.manager.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.lib.PropertyHelpers
import music.manager.lib.readTagProperty
import music.manager.ui.ExportPathList
import music.manager.ui.FileTextField
import music.manager.viewmodels.SettingsViewModel

@Composable
fun SettingsModal(settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }) {
    val settingsState by settingsViewModel.settingsState.collectAsState()

    val sourceSongsLauncher = rememberDirectoryPickerLauncher { file ->
        settingsViewModel.setSourceDirectory(file?.path ?: "")
    }
    val outputSongsLauncher = rememberDirectoryPickerLauncher { file ->
        settingsViewModel.setOutputDirectory(file?.path ?: "")
    }

    Dialog(
        onDismissRequest = { settingsViewModel.open() }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(32.dp)) {
                FileTextField(sourceSongsLauncher, settingsState.sourceDirectory, "Source Folder")
                FileTextField(outputSongsLauncher, settingsState.outputDirectory, "Export Folder")
                ExportPathList()
            }
        }
    }
}