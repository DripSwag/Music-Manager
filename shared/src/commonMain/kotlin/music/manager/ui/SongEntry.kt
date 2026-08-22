package music.manager.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.classes.Song
import music.manager.viewmodels.SongsViewModel

@Composable
fun SongEntry(index: Int, songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsStateWithLifecycle()
    val song = uiState.songs[index]

    Card(onClick = { handleClick(songsViewModel, index) }) {
        Text(song.sourceSongName)
        Text(song.songName)
    }
}

fun handleClick(songsViewModel: SongsViewModel, index: Int) {
    songsViewModel.setEditingSong(index)
}