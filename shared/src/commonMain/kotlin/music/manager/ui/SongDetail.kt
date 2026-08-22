package music.manager.ui

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.classes.Song
import music.manager.viewmodels.SongsViewModel

@Composable
fun SongDetail(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsState()
    val song = uiState.songs[uiState.editingSongIndex]

    Card {
        EditModalTextField(song.songName) { newValue -> songsViewModel.editSong { it.songName = newValue } }
        EditModalTextField(song.artist) { newValue -> songsViewModel.editSong { it.artist = newValue } }
        EditModalTextField(song.genre) { newValue -> songsViewModel.editSong { it.genre = newValue } }
        EditModalTextField(song.album) { newValue -> songsViewModel.editSong { it.album = newValue } }
    }
}