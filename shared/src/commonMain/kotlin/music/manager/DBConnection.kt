package music.manager

import musicmanager.shared.generated.resources.Res
import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.insert

const val SOURCE_SONG_NAME_MAX_LENGTH = 512

object Songs : Table("songs") {
    val sourceSongName = varchar("source_song_name", length = SOURCE_SONG_NAME_MAX_LENGTH)

    override val primaryKey = PrimaryKey(sourceSongName)
}

class Connection {
    fun connect() {
        Database.connect("jdbc:sqlite:data", driver = "org.sqlite.JDBC")
    }
}
