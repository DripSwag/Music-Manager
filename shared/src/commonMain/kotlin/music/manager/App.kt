package music.manager

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

import music.manager.database.ConnectDB
import music.manager.ui.Landing

@Composable
@Preview
fun App() {
    ConnectDB()

    Landing()
}
