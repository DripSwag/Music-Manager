package music.manager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import music.manager.classes.Song
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.noImg
import org.jetbrains.compose.resources.painterResource


@Composable
fun SongArtworkImage(song: Song, modifier: Modifier, placeholder: Boolean = true) {
    if (song.coverArtBytes != null) {
        AsyncImage(
            model = song.coverArtBytes,
            contentDescription = "Cover Art",
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    } else if (placeholder) {
        Image(
            painter = painterResource(Res.drawable.noImg),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
            contentDescription = "Cover Art",
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    }
}