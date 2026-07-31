package music.manager.lib

import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.UserDefinedFileAttributeView

class SongSourceReader {
    companion object {
        fun readAllSourceSongs(directory: File): List<String> {
            for (file: File in directory.listFiles()) {
            }
            return ArrayList<String>()
        }
    }
}