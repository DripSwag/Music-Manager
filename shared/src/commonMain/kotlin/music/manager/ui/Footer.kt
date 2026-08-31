package music.manager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.ui.btn.ExportSongsBTN
import music.manager.ui.btn.RefreshSongsBTN
import music.manager.viewmodels.SettingsViewModel
import musicmanager.shared.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import java.io.File
import musicmanager.shared.generated.resources.settings

/**
 * Contains extra buttons like refresh songs and settings
 */
@Composable
@Preview
fun Footer(settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }) {
    BottomAppBar(
        actions = {
            ExportSongsBTN()
            RefreshSongsBTN()
            IconButton(onClick = { settingsViewModel.open() }, modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)) {
                Image(
                    painterResource(Res.drawable.settings),
                    "Settings",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentPadding = PaddingValues(32.dp, 0.dp),
    )
}
