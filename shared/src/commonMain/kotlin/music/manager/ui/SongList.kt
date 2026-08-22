package music.manager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.viewmodels.SongsViewModel

/**
 * Displays all songs in selected input directory
 */
@Composable
@Preview
fun SongList(viewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        for ((index) in uiState.songs.withIndex()) {
            SongEntry(index)
        }
    }
}