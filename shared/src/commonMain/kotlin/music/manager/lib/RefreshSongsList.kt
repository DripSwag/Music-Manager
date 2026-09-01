package music.manager.lib

import music.manager.classes.Database
import music.manager.classes.Song
import music.manager.classes.getProperty
import music.manager.enum.SettingsProperty
import java.io.File

fun getSongsData(): ArrayList<Song> {
    val songsSourceDirectory = File(getProperty(SettingsProperty.SOURCE_DIRECTORY_PROPERTY.propertyName))

    if (songsSourceDirectory.isDirectory()) {
        val sourceSongs = readAllSourceSongs(songsSourceDirectory)

        checkSongsWithDatabase(sourceSongs)

        return sourceSongs
    }
    return ArrayList()
}

fun checkSongsWithDatabase(sourceSongs: List<Song>) {
    for (song: Song in sourceSongs) {
        if (Database.searchSong(song)) {
            getExistingSongData(song)
        } else {
            Database.addSong(song)
        }
    }
}

fun getExistingSongData(song: Song) {
    val existingData = Database.getSong(song)
    song.songName = existingData.songName
    song.artist = existingData.artist
    song.genre = existingData.genre
    song.album = existingData.album
    song.coverArt = existingData.coverArt
}