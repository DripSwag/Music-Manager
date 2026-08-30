package music.manager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.classes.Song
import music.manager.viewmodels.SongsViewModel

@Composable
fun SongDetail(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsState()
    val song = uiState.songs[uiState.editingSongIndex]

    val launcher = rememberFilePickerLauncher {
        songsViewModel.editSong { song -> song.coverArt = it }
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.width(IntrinsicSize.Max).widthIn(0.dp, 500.dp).fillMaxHeight()
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SongArtworkImage(song, Modifier.aspectRatio(1.0f).fillMaxWidth().clip(RoundedCornerShape(8.dp)))
            SongTextField(
                song.songName,
                label = "Title",
                onValueChange = { newValue, song -> song.songName = newValue })
            SongTextFieldDropdown(
                song.artist,
                label = "Artist",
                filter = { it.artist },
                onValueChange = { newValue, song -> song.artist = newValue })
            SongTextFieldDropdown(
                song.album,
                label = "Album",
                filter = { it.album },
                onValueChange = { newValue, song -> song.album = newValue })
            SongTextField(song.genre, label = "Genre", onValueChange = { newValue, song -> song.genre = newValue })
            FileTextField(launcher, song.coverArt?.path ?: "", "Cover Art")
            TextField(
                readOnly = true,
                value = song.sourceSongName,
                onValueChange = { },
                label = { Text("File") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}