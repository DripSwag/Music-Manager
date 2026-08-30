package music.manager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.onClick
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Label
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.classes.Song
import music.manager.viewmodels.SongsViewModel

@Composable
fun SongTextField(
    value: String,
    label: String,
    onValueChange: (String, Song) -> Unit,
    songsViewModel: SongsViewModel = viewModel { SongsViewModel() },
) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            songsViewModel.editSong { song ->
                onValueChange(newValue, song)
            }
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        readOnly = false,
    )
}