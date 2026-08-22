package music.manager.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.ui.btn.ExportSongsBTN
import music.manager.ui.btn.RefreshSongsBTN
import music.manager.ui.btn.SelectSongsOutputBTN
import music.manager.ui.btn.SelectSongsSourceBTN
import java.io.File

/**
 * Contains extra buttons like refresh songs and settings
 */
@Composable
@Preview
fun Footer() {
    SelectSongsSourceBTN()
    RefreshSongsBTN()
    SelectSongsOutputBTN()
    ExportSongsBTN()
}
