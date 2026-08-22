package music.manager.database

import music.manager.classes.Song
import music.manager.database.tables.SongTable
import music.manager.database.tables.SongTable.genre
import music.manager.database.tables.SongTable.songName
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update

fun ConnectDB() {
    Database.connect("jdbc:sqlite:data", driver = "org.sqlite.JDBC")

    transaction {
        SchemaUtils.create(SongTable)
    }
}

fun updateSong(song: Song) {
    transaction {
        SongTable.update({ SongTable.sourceSongName eq song.sourceSongName }) {
            it[songName] = song.songName
            it[genre] = song.genre
            it[artist] = song.artist
            it[album] = song.album
        }
    }
}