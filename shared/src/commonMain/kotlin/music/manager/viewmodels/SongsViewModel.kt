package music.manager.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import music.manager.classes.Song
import music.manager.database.tables.SongTable
import music.manager.database.updateSong
import music.manager.lib.loadSongsFromDatabase
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.update

data class SongsState(
    val songs: ArrayList<Song> = loadSongsFromDatabase(),
    var editingSongIndex: Int = -1,
    var editing: Boolean = false
)

class SongsViewModel : ViewModel() {
    val uiState: StateFlow<SongsState>
        field = MutableStateFlow(SongsState())

    fun setSongs(songs: ArrayList<Song>) {
        uiState.update { it.copy(songs = songs) }
    }

    fun setEditingSong(index: Int) {
        if (index == uiState.value.editingSongIndex && uiState.value.editing) {
            uiState.update { it.copy(editing = false) }
        } else {
            uiState.update { it.copy(editingSongIndex = index, editing = true) }
        }
    }

    // Need to create a new copy of Songs list and the modified song to cause rehydration
    fun editSong(editSong: (Song) -> Unit): ArrayList<Song> {
        val value = uiState.value
        val song = value.songs[value.editingSongIndex]

        val songCopy = Song(song.sourceSongName, song.songName, song.genre, song.artist, song.album)
        editSong(songCopy)

        updateSong(songCopy)

        val newList = ArrayList<Song>()
        for (index in uiState.value.songs.indices) {
            if (index != value.editingSongIndex) {
                newList.add(value.songs[index])
            } else {
                newList.add(songCopy)
            }
        }

        uiState.update { it.copy(songs = newList) }

        return newList
    }
}
