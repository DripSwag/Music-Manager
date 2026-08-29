package music.manager.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import music.manager.classes.Song
import music.manager.database.updateDBSong
import music.manager.enum.SongSortingComparator
import music.manager.lib.getSongsData

val INITIAL_SORTING_COMPARATOR = SongSortingComparator.TITLE_ASCENDING

data class SongsState(
    val songs: ArrayList<Song> = sortList(getSongsData(), INITIAL_SORTING_COMPARATOR),
    var editingSongIndex: Int = -1,
    var editing: Boolean = false,
    var sortingComparator: SongSortingComparator = INITIAL_SORTING_COMPARATOR,
)

class SongsViewModel : ViewModel() {
    val uiState: StateFlow<SongsState>
        field = MutableStateFlow(SongsState())

    fun setSongs(songs: ArrayList<Song>) {
        uiState.update { it.copy(songs = sortList(songs, it.sortingComparator)) }
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

        val songCopy = value.songs[value.editingSongIndex].copy()
        editSong(songCopy)

        updateDBSong(songCopy)

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

    fun sortSongs(comparator: SongSortingComparator) {
        uiState.update { it.copy(sortingComparator = comparator, songs = sortList(uiState.value.songs, comparator)) }
    }

}

private fun sortList(list: ArrayList<Song>, comparator: SongSortingComparator): ArrayList<Song> {
    val newList = ArrayList(list.sortedWith(comparator.comparator))
    return newList
}
