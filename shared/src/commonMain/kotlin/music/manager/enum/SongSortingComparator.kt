package music.manager.enum

import music.manager.classes.Song

enum class SongSortingComparator(val comparator: Comparator<in Song>) {
    TITLE_ASCENDING(compareBy { it.songName.lowercase() }),
    TITLE_DESCENDING(compareByDescending { it.songName.lowercase() }),
    ARTIST_ASCENDING(compareBy { it.artist.lowercase() }),
    ARTIST_DESCENDING(compareByDescending { it.artist.lowercase() }),
}