package music.manager.ui.btn

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.enum.SongProperties
import music.manager.lib.exportSongs
import music.manager.viewmodels.SongsViewModel
import java.util.LinkedList

@Composable
fun ExportSongsBTN(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsState()

    val songProperties = LinkedList<SongProperties>()
    songProperties.add(SongProperties.GENRE)

    Button(onClick = { exportSongs(uiState.songs, songProperties) }) {
        Text("Export")
    }
}