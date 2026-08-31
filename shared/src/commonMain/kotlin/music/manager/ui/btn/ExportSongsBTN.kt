package music.manager.ui.btn

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.enum.SongProperties
import music.manager.lib.exportSongs
import music.manager.viewmodels.SettingsViewModel
import music.manager.viewmodels.SongsViewModel
import java.util.LinkedList

@Composable
fun ExportSongsBTN(
    songsViewModel: SongsViewModel = viewModel { SongsViewModel() },
    settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }
) {
    val uiState by songsViewModel.uiState.collectAsState()
    val settingsState by settingsViewModel.settingsState.collectAsState()

    Button(
        onClick = { exportSongs(uiState.songs, settingsState.properties) },
        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
    ) {
        Text("Export")
    }
}