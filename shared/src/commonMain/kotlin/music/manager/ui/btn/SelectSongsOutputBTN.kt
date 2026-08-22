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
fun SelectSongsOutputBTN() {
    val outputSongsLauncher = rememberDirectoryPickerLauncher { file ->
        PropertyHelpers.writeProperty("songsOutputDirectory", file?.path ?: "")
    }

    Button(
        onClick = { outputSongsLauncher.launch() }
    ) {
        Text("Pick Output Directory")
    }
}