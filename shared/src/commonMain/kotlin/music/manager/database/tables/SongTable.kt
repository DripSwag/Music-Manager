package music.manager.database.tables

import org.jetbrains.exposed.v1.core.*

const val SONG_NAME_MAX_LENGTH = 512

object SongTable : Table("song") {
    val sourceSongName = varchar("source_song_name", length = SONG_NAME_MAX_LENGTH)
    val songName = varchar("song_name", length = SONG_NAME_MAX_LENGTH)
    val genre = varchar("genre", length = 128).default("")
    val artist = varchar("artist", length = 256).default("")
    val album = varchar("album", length = 256).default("")
    val coverArt = varchar("cover_art", length = 1024).nullable().default(null)

    override val primaryKey = PrimaryKey(sourceSongName)
}