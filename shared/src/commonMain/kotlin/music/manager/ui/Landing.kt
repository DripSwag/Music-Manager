package music.manager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme

@Composable
@Preview
fun Landing(){
    MaterialTheme {
        Column {
            Row {
                SongList()
                SongDetail()
            }
            Footer()
        }
    }
}