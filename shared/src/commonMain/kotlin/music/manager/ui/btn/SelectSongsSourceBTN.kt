package music.manager.ui.btn

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vinceglb.filekit.dialogs.compose.rememberDirectoryPickerLauncher
import io.github.vinceglb.filekit.path
import music.manager.lib.PropertyHelpers


@Composable
@Preview
fun SelectSongsSourceBTN() {
    val sourceSongsLauncher = rememberDirectoryPickerLauncher { file ->
        PropertyHelpers.writeProperty("songsSourceDirectory", file?.path ?: "")
    }

    Button(
        onClick = { sourceSongsLauncher.launch() }
    ) {
        Text("Pick Source Directory")
    }
}