package music.manager.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.viewmodels.SongsViewModel

@Composable
@Preview
fun Landing(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsState()

    MaterialTheme {
        Column {
            Row {
                SongList()
                if (uiState.editing) {
                    SongDetail()
                }
            }
            Footer()
        }
    }
}