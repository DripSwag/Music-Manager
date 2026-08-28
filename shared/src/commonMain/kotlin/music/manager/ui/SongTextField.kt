package music.manager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.onClick
import androidx.compose.material3.Label
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SongTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
