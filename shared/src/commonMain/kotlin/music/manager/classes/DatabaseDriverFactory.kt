package music.manager.classes

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.util.Properties
import dev.database.Database

class DatabaseDriverFactory {
    fun createDriver(): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:data.db", Properties(), Database.Schema)
    }
}