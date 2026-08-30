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
import androidx.compose.ui.window.MenuComposable
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.classes.Song
import music.manager.viewmodels.SongsViewModel
import kotlin.comparisons.compareBy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongTextFieldDropdown(
    value: String,
    label: String,
    onValueChange: (String, Song) -> Unit,
    filter: (Song) -> String,
    songsViewModel: SongsViewModel = viewModel { SongsViewModel() },
) {
    var expanded by remember { mutableStateOf(false) }
    val songsState by songsViewModel.uiState.collectAsState()
    val songsContainingInputList = songsContainingInput(songsState.songs, songsState.editingSongIndex, filter)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = value,
            onValueChange = { newValue ->
                songsViewModel.editSong { song ->
                    onValueChange(newValue, song)
                }
            },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            readOnly = false,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        if (songsContainingInputList.isNotEmpty()) {
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                songsContainingInputList.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            songsViewModel.editSong { song ->
                                onValueChange(option, song)
                                expanded = false
                            }
                        }
                    )
                }
            }
        }
    }
}

private fun songsContainingInput(
    songs: ArrayList<Song>,
    editingSongIndex: Int,
    filter: (Song) -> String
): List<String> {

    val editingSong = songs[editingSongIndex]
    val editingSongValue = filter(editingSong).lowercase()

    val otherSongs = songs.toMutableList()
    otherSongs.remove(editingSong)

    val distinctValues = otherSongs.map(filter).distinct()

    return distinctValues.filter {
        it.lowercase().contains(editingSongValue.lowercase())
        it != ""
    }.sortedWith(compareBy { it.lowercase() })

}