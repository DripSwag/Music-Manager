package music.manager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.onClick
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.backgroundDark
import music.manager.viewmodels.SongsViewModel

/**
 * Displays all songs in selected input directory
 */
@Composable
@Preview
fun SongList(viewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
    ) {
        if (uiState.songs.isNotEmpty()) {
            LazyColumn(Modifier.padding(12.dp)) {
                items(uiState.songs.size) { index ->
                    SongEntry(index)
                }
            }
        } else {
            NoSongsFound()
        }
    }
}

@Composable
fun NoSongsFound() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("No Songs Found!", fontWeight = FontWeight.Bold, fontSize = 64.sp)
        Text("Add songs to the source folder and refresh the list", fontSize = 24.sp)
    }
}