package music.manager.ui.btn

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.lib.getSongsData
import music.manager.viewmodels.SongsViewModel
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.refresh
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun RefreshSongsBTN(songsViewModel: SongsViewModel = viewModel { SongsViewModel() }) {
    IconButton(onClick = { handleClick(songsViewModel) }
    ) {
        Image(
            painter = painterResource(Res.drawable.refresh),
            contentDescription = "Refresh",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
}

fun handleClick(songsViewModel: SongsViewModel) {
    val songs = getSongsData()

    songsViewModel.setSongs(songs)
}