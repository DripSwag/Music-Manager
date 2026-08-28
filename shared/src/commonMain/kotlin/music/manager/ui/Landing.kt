package music.manager.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import music.manager.ui.btn.ExportSongsBTN
import music.manager.ui.btn.RefreshSongsBTN
import music.manager.viewmodels.SettingsViewModel
import music.manager.viewmodels.SongsViewModel
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.settings
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun Landing(
    songsViewModel: SongsViewModel = viewModel { SongsViewModel() }
) {
    val uiState by songsViewModel.uiState.collectAsState()

    Scaffold(bottomBar = { Footer() }) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(32.dp, 16.dp)) {
            if (uiState.editing) {
                SongDetail()
            }
            SongList()
        }
    }
}