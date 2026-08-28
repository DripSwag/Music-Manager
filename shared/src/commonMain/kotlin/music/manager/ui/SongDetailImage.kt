package music.manager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import music.manager.viewmodels.SongsViewModel
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.path
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.noImg
import org.jetbrains.compose.resources.painterResource

@Composable
fun SongDetailImage(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    val uiState by songsViewModel.uiState.collectAsState()

    AsyncImage(
        model = uiState.songs[uiState.editingSongIndex].coverArtBytes,
        contentDescription = "Cover Art",
        contentScale = ContentScale.Crop,
        modifier = Modifier.aspectRatio(1.0f).fillMaxWidth().clip(RoundedCornerShape(8.dp)),
    )
}