package music.manager.lib

import androidx.compose.runtime.Composable
import io.github.vinceglb.filekit.PlatformFile
import music.manager.classes.Song
import music.manager.database.addDBSong
import music.manager.database.tables.SongTable
import music.manager.viewmodels.SongsViewModel
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.io.File

fun getSongsData(): ArrayList<Song> {
    val songsSourceDirectory = File(PropertyHelpers.readProperty("songsSourceDirectory"))

    if (songsSourceDirectory.isDirectory()) {
        val sourceSongs = readAllSourceSongs(songsSourceDirectory)

        checkSongsWithDatabase(sourceSongs)

        return sourceSongs
    }
    return ArrayList()
}

fun checkSongsWithDatabase(sourceSongs: List<Song>) {
    for (song: Song in sourceSongs) {
        if (sourceSongExists(song.sourceSongName)) {
            getExistingSongData(song)
        } else {
            addDBSong(song)
        }
    }
}

fun getExistingSongData(song: Song) {
    transaction {
        SongTable.selectAll()
            .where { SongTable.sourceSongName eq song.sourceSongName }
            .map {
                song.songName = it[SongTable.songName]
                song.genre = it[SongTable.genre]
                song.album = it[SongTable.album]
                song.artist = it[SongTable.artist]
                if (it[SongTable.coverArt] == null) {
                    song.coverArt = null
                } else {
                    song.coverArt = PlatformFile(it[SongTable.coverArt]!!)
                }
            }
    }
}


fun sourceSongExists(songName: String): Boolean {
    val count = transaction {
        SongTable.select(SongTable.sourceSongName)
            .where { SongTable.sourceSongName eq songName }
            .count()
    }

    return count > 0
}