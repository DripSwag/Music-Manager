package music.manager.lib

import org.farng.mp3.MP3File
import java.io.File

class SongSourceReader {
    companion object {
        fun readAllSourceSongs(directory: File): List<String> {
            for (file: File in directory.listFiles()) {
                handleExtension(file)
            }
            return ArrayList<String>()
        }
    }
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