package music.manager.ui.modal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import music.manager.viewmodels.ErrorViewModel
import musicmanager.shared.generated.resources.Res
import musicmanager.shared.generated.resources.error
import org.jetbrains.compose.resources.painterResource

@Composable
fun OutputDirectoryErrorModal(
    errorViewModel: ErrorViewModel = viewModel { ErrorViewModel() }
) {
    Dialog(
        onDismissRequest = { errorViewModel.toggleOutputDirectory() }
    ) {
        Card {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(Res.drawable.error),
                    contentDescription = "Error",
                    modifier = Modifier.fillMaxWidth(0.4f),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.errorContainer)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("No output folder provided", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                Text("Please select a folder in the settings")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { errorViewModel.toggleOutputDirectory() }) {
                    Text("Close")
                }
            }
        }
    }
}