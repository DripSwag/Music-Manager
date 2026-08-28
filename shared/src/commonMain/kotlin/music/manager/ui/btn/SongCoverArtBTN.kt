package music.manager.ui.btn

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import music.manager.viewmodels.SongsViewModel

@Composable
fun SongCoverArtBTN(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val handleOnClick = rememberFilePickerLauncher {
        songsViewModel.editSong { song -> song.coverArt = it }
    }

    Button(
        onClick = { handleOnClick.launch() },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Select Cover Art")
    }
}