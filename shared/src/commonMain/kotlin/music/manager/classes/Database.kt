package music.manager.classes

import dev.database.Database
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.path
import io.github.vinceglb.filekit.utils.toPath

object Database {
    val database: Database = Database(DatabaseDriverFactory().createDriver())
    val songQueries = database.songQueries

    fun addSong(song: Song) {
        songQueries.insert(song.sourceSongName, song.songName, song.genre, song.artist, song.album, song.coverArt?.path)
    }

    fun updateSong(song: Song) {
        songQueries.update(song.songName, song.genre, song.artist, song.album, song.coverArt?.path, song.sourceSongName)
    }

    fun getSong(song: Song): Song {
        val row = songQueries.get(song.sourceSongName).executeAsList()[0]
        val coverArt = if (row.cover_art != null) PlatformFile(row.cover_art) else null
        return Song(row.source_song_name, row.song_name, row.genre, artist = row.artist, row.album, coverArt)
    }

    fun searchSong(song: Song): Boolean {
        return songQueries.search(song.sourceSongName).executeAsOne()
    }
}