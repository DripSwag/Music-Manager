package music.manager.ui.btn

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.enum.SongSortingComparator
import music.manager.viewmodels.SongsState
import music.manager.viewmodels.SongsViewModel
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.chevronDown
import musicmanager.shared.generated.resources.chevronUp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SortSongBTN(
    label: String,
    ascendingComparator: SongSortingComparator,
    descendingComparator: SongSortingComparator,
    songsViewModel: SongsViewModel = viewModel { SongsViewModel() }
) {
    val songsState by songsViewModel.uiState.collectAsState()

    FilterChip(
        label = { Text(label) },
        onClick = {
            when (songsState.sortingComparator) {
                ascendingComparator -> songsViewModel.sortSongs(descendingComparator)
                else -> songsViewModel.sortSongs(ascendingComparator)
            }
        },
        leadingIcon = { LeadingIcon(ascendingComparator, descendingComparator) },
        selected = songsState.sortingComparator == ascendingComparator || songsState.sortingComparator == descendingComparator,
    )
}

@Composable
private fun LeadingIcon(
    ascendingComparator: SongSortingComparator,
    descendingComparator: SongSortingComparator,
    songsViewModel: SongsViewModel = viewModel { SongsViewModel() }
) {
    val songsState by songsViewModel.uiState.collectAsState()
    val chevron: DrawableResource? = when (songsState.sortingComparator) {
        ascendingComparator -> Res.drawable.chevronUp
        descendingComparator -> Res.drawable.chevronDown
        else -> null
    }

    if (chevron != null) {
        Icon(
            painter = painterResource(chevron),
            contentDescription = "Filter Direction",
            modifier = Modifier.size(FilterChipDefaults.IconSize)
        )
    }

}