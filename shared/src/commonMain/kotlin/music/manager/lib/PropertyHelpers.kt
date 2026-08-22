package music.manager.lib

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.util.Properties

const val SETTINGS_FILE_NAME = "settings.properties"

class PropertyHelpers {
    companion object {
        fun writeProperty(key: String, value: String) {
            val settings = Properties()

            File(SETTINGS_FILE_NAME).createNewFile()

            val fileStreamIn = FileInputStream(SETTINGS_FILE_NAME)
            settings.load(fileStreamIn)
            fileStreamIn.close()

            settings.setProperty(key, value)

            val fileStreamOut = FileOutputStream(SETTINGS_FILE_NAME)
            settings.store(fileStreamOut, "")
            fileStreamOut.close()
        }

        fun readProperty(key: String): String {
            val settings = Properties()

            val fileStreamIn = FileInputStream(SETTINGS_FILE_NAME)
            settings.load(fileStreamIn)

            val result = settings.getProperty(key)

            fileStreamIn.close()

            return result
        }
    }
}