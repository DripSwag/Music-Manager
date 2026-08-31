package music.manager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.enum.SongProperties
import music.manager.viewmodels.SettingsViewModel
import musicmanager.shared.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState
import musicmanager.shared.generated.resources.drag

@Composable
fun ExportPathList(settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }) {
    val settingsState by settingsViewModel.settingsState.collectAsState()

    val lazyListState = rememberLazyListState()
    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        val temp = settingsState.properties.toMutableList().apply {
            add(to.index - 1, removeAt(from.index - 1))
        }
        settingsViewModel.setProperties(temp)
    }

    LazyColumn(state = lazyListState) {
        item {
            Text("Export Folder Structure", fontSize = 32.sp)
        }
        items(settingsState.properties, key = { it }) {
            ReorderableItem(reorderableLazyListState, key = it) { isDragging ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = settingsState.properties[settingsState.properties.indexOf(it)].enabled,
                            onCheckedChange = { value -> settingsViewModel.setEnabled(it) }
                        )
                        Text(it.property.toString())
                    }
                    IconButton(onClick = {}) {
                        Image(
                            painterResource(Res.drawable.drag),
                            "Drag",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outline)
                        )
                    }
                }
            }
        }
    }
}