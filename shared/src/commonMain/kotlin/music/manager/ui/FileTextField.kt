package music.manager.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import io.github.vinceglb.filekit.dialogs.compose.PickerResultLauncher
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.folder
import org.jetbrains.compose.resources.painterResource

@Composable
fun FileTextField(launcher: PickerResultLauncher, value: String, label: String) {
    TextField(
        value = value,
        label = { Text(label) },
        onValueChange = {},
        trailingIcon = { FileIconBTN(launcher) },
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
    )
}

@Composable
private fun FileIconBTN(launcher: PickerResultLauncher) {
    IconButton(onClick = { launcher.launch() }) {
        Icon(
            painter = painterResource(Res.drawable.folder),
            contentDescription = "Pick File",
            modifier = Modifier.fillMaxWidth(0.7f).aspectRatio(1f).pointerHoverIcon(PointerIcon.Hand)
        )
    }
}