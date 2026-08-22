package music.manager.lib

import music.manager.classes.Song
import music.manager.database.tables.SongTable
import org.farng.mp3.MP3File
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.exists
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.io.File

fun readAllSourceSongs(directory: File): ArrayList<Song> {
    val songs = ArrayList<Song>()

    for (file: File in directory.listFiles()) {
        // TODO: Add file reading for genre, artist, etc.
        val song = Song(file.name, file.nameWithoutExtension)
        songs.add(song)
    }

    return songs
}


fun handleExtension(file: File) {
    if (file.extension == "mp3") {
        handleMP3(file)
    }
}

fun handleMP3(file: File) {
    val mp3File = MP3File(file)
    if (mp3File.hasID3v2Tag()) {
        val tag = mp3File.iD3v2Tag
        println(tag.leadArtist)
    }
}