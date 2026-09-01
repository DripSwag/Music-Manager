package music.manager.classes

import kotlinx.io.Buffer
import kotlinx.io.files.FileNotFoundException
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readLine
import kotlinx.io.writeString
import music.manager.enum.SettingsProperty

const val SETTINGS_FILE_NAME = "settings.properties"
val PATH = Path(SETTINGS_FILE_NAME)

fun getProperty(key: String): String {
    return getProperties().find { it.first == key }?.second ?: ""
}

fun writeProperty(key: String, value: String) {
    val properties = getProperties()
    val propertyIndex = properties.indexOfFirst { it.first == key }

    if (propertyIndex != -1) {
        properties[propertyIndex] = Pair(key, value)
    } else {
        properties.add(Pair(key, value))
    }

    val sink = SystemFileSystem.sink(PATH)
    val buffer = Buffer()
    val str = generateSettingsString(properties)

    buffer.writeString(str)

    sink.write(buffer, str.toByteArray().size.toLong())
}

private fun generateSettingsString(list: ArrayList<Pair<String, String>>): String {
    var str = ""

    for ((index, property) in list.withIndex()) {
        str += "${property.first}=${property.second}"
        if (index != list.size) {
            str += "\n"
        }
    }

    return str
}

private fun getProperties(): ArrayList<Pair<String, String>> {
    val value = ArrayList<Pair<String, String>>()
    val buffer = Buffer()

    try {
        val source = SystemFileSystem.source(PATH)

        while (source.readAtMostTo(buffer, 1) != (-1).toLong()) {
        }

        var line = buffer.readLine()

        while (line != null) {
            if (line.contains("=")) {
                val keyValue = line.split("=", limit = 2)

                value.add(Pair(keyValue[0], keyValue[1]))
            }

            line = buffer.readLine()
        }

        source.close()

        return value
    } catch (e: FileNotFoundException) {
        return value
    }
}
