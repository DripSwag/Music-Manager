package music.manager.ui.btn

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.lib.getSongsData
import music.manager.viewmodels.SongsViewModel

@Composable
@Preview
fun RefreshSongsBTN(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    Button(onClick = { handleClick(songsViewModel) }
    ) { Text("Refresh") }
}

fun handleClick(songsViewModel: SongsViewModel) {
    val songs = getSongsData()

    songsViewModel.setSongs(songs)
}