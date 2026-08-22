package music.manager.lib

import androidx.compose.runtime.Composable
import music.manager.classes.Song
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

    val sourceSongs = readAllSourceSongs(songsSourceDirectory)

    checkSongsWithDatabase(sourceSongs)

    // TODO: Need to check songs that have been deleted from source and delete from DB

    return sourceSongs
}

fun checkSongsWithDatabase(sourceSongs: List<Song>) {
    for (song: Song in sourceSongs) {
        if (sourceSongExists(song.sourceSongName)) {
            getExistingSongData(song)
        } else {
            addSong(song.sourceSongName, song.songName)
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
            }
    }
}

fun addSong(sourceSongName: String, songName: String) {
    transaction {
        SongTable.insert {
            it[this.sourceSongName] = sourceSongName
            it[this.songName] = songName
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

fun loadSongsFromDatabase(): ArrayList<Song> {
    val result = ArrayList<Song>()

    transaction {
        SongTable.selectAll().forEach {
            result.add(
                Song(
                    it[SongTable.sourceSongName],
                    it[SongTable.songName],
                    it[SongTable.genre],
                    it[SongTable.artist],
                    it[SongTable.album]
                )
            )
        }
    }

    return result
}