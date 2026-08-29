package music.manager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.backgroundDark
import music.manager.classes.Song
import music.manager.enum.SongSortingComparator
import music.manager.ui.btn.SortSongBTN
import music.manager.viewmodels.SongsViewModel
import java.util.Comparator

/**
 * Displays all songs in selected input directory
 */
@Composable
@Preview
fun SongList(viewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.fillMaxHeight().fillMaxWidth().clip(RoundedCornerShape(8.dp))
    ) {
        if (uiState.songs.isNotEmpty()) {
            Column(Modifier.padding(12.dp)) {
                Filters()
                LazyColumn {
                    items(uiState.songs.size) { index ->
                        SongEntry(index)
                    }
                }
            }
        } else {
            NoSongsFound()
        }
    }
}

@Composable
private fun NoSongsFound() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("No Songs Found!", fontWeight = FontWeight.Bold, fontSize = 64.sp)
        Text("Add songs to the source folder and refresh the list", fontSize = 24.sp)
    }
}

@Composable
private fun Filters() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        SortSongBTN(
            "Title",
            SongSortingComparator.TITLE_ASCENDING,
            SongSortingComparator.TITLE_DESCENDING
        )
        SortSongBTN(
            "Artist",
            SongSortingComparator.ARTIST_ASCENDING,
            SongSortingComparator.ARTIST_DESCENDING
        )
    }
}