package music.manager

import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.insert

object Songs : Table("songs") {
    val id = integer("id").autoIncrement()

    override val primaryKey = PrimaryKey(id)
}

class Connection {
    fun connect() {
        Database.connect("jdbc:sqlite:data", driver = "org.sqlite.JDBC")
    }

    fun something() {
        var id: Int = 0

        transaction {
            SchemaUtils.create(Songs)

            id = Songs.insert {} get Songs.id
        }
        System.out.println(id)
    }
}
