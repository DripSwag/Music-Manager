package music.manager.classes

import coil3.Bitmap
import coil3.BitmapImage
import coil3.request.ImageRequest
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.path
import kotlinx.io.files.FileSystem
import org.jaudiotagger.kt.AudioFile
import org.jaudiotagger.kt.AudioTagger
import kotlinx.io.files.Path
import music.manager.lib.PropertyHelpers
import org.jaudiotagger.kt.tag.FieldKey
import org.jetbrains.exposed.v1.core.exists
import java.io.File
import java.nio.file.Files
import javax.sound.sampled.spi.AudioFileReader

class Song(
    var sourceSongName: String,
    var songName: String,
    var genre: String = "",
    var artist: String = "",
    var album: String = "",
    coverArt: PlatformFile? = null
) {
    var coverArt = coverArt
        set(value) {
            coverArtBytes = fileCoverArt(value)
            field = value
        }

    var coverArtBytes: ByteArray? = fileCoverArt(coverArt)

    private fun fileCoverArt(file: PlatformFile?): ByteArray? {
        if (file == null) {
            val pathString = PropertyHelpers.readProperty("songsSourceDirectory") + "/${sourceSongName}"

            if (File(pathString).exists()) {
                val path = Path(pathString)
                val audioFile = AudioTagger.read(path)

                if (audioFile.tag.artworks.isNotEmpty()) {
                    return audioFile.tag.artworks[0].data
                }
            }

            return null
        } else {
            if (file.file.isFile()) {
                return Files.readAllBytes(file.file.toPath())
            }

            return null
        }
    }

    fun copy(): Song {
        val song = Song(this.sourceSongName, this.songName, this.genre, this.artist, this.album, this.coverArt)
        song.coverArtBytes = this.coverArtBytes
        return song
    }
}