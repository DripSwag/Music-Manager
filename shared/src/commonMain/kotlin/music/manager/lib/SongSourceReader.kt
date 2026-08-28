package music.manager.lib

import kotlinx.io.files.Path
import music.manager.classes.Song
import music.manager.database.tables.SongTable
import org.jaudiotagger.kt.AudioTagger
import org.jaudiotagger.kt.tag.FieldKey
import org.jaudiotagger.kt.tag.Tag
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.exists
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.io.File

fun readAllSourceSongs(directory: File): ArrayList<Song> {
    val songs = ArrayList<Song>()

    for (file: File in directory.listFiles()) {
        val audioFile = AudioTagger.read(Path(file.path))
        val tag = audioFile.tag

        val song = Song(
            file.name,
            file.nameWithoutExtension,
            readTagProperty(FieldKey.GENRE, tag),
            readTagProperty(FieldKey.ARTIST, tag),
            readTagProperty(FieldKey.ALBUM, tag)
        )

        songs.add(song)
    }

    return songs
}

fun readTagProperty(fieldKey: FieldKey, tag: Tag): String {
    return tag.first(fieldKey) ?: ""
}

fun extractArtistFromFileName(song: Song, file: File) {
    val parsedSongName = parseFileName(file.nameWithoutExtension)

    if (parsedSongName != null) {
        song.songName = parsedSongName.second
        song.artist = parsedSongName.first
    }
}

fun parseFileName(fileName: String): Pair<String, String>? {
    val list = fileName.split(" - ", limit = 2)
    if (list.size == 2) {
        return Pair(list[0], list[1])
    }
    return null
}