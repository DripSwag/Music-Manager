package music.manager.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.absolutePath
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.lib.SongSourceReader
import java.io.File

/**
 * Contains extra buttons like refresh songs and settings
 */
@Composable
@Preview
fun Footer() {
    var songSourceDirectory: File? = null

    val launcher = rememberDirectoryPickerLauncher { file ->
        songSourceDirectory = file?.file
    }

    Button(
        onClick = { launcher.launch() }
    ) {
        Text("Pick Source Directory")
    }

    Button(onClick = { handleRefreshClick(songSourceDirectory) }
    ) { Text("Refresh") }
}

fun handleRefreshClick(songSourceDirectory: File?) {
    if (songSourceDirectory != null) {
        SongSourceReader.readAllSourceSongs(songSourceDirectory)
    }
}