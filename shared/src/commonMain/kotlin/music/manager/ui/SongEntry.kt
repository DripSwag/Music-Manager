package music.manager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import io.github.vinceglb.filekit.path
import music.manager.classes.Song
import music.manager.viewmodels.SongsViewModel

@Composable
fun SongEntry(index: Int, songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsStateWithLifecycle()
    val song = uiState.songs[index]
    val color =
        if (uiState.editingSongIndex == index && uiState.editing) MaterialTheme.colorScheme.surfaceContainerHighest else Color.Transparent

    Row(
        modifier = Modifier.clip(RoundedCornerShape(8.dp)).pointerHoverIcon(PointerIcon.Hand)
            .background(color)
            .clickable {
                handleClick(songsViewModel, index)
            }.padding(16.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SongArtworkImage(song, Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)), false)
        Column() {
            Text(song.songName, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
            Text(
                song.artist,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = TextUnit(14f, TextUnitType.Sp)
            )
        }
    }
}

fun handleClick(songsViewModel: SongsViewModel, index: Int) {
    songsViewModel.setEditingSong(index)
}