package music.manager.classes

import io.github.vinceglb.filekit.PlatformFile
import org.jaudiotagger.kt.AudioTagger
import kotlinx.io.files.Path
import java.io.File
import java.nio.file.Files


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
            val pathString = getProperty("songsSourceDirectory") + "/${sourceSongName}"

            if (File(pathString).exists()) {
                val path = Path(pathString)
                val audioFile = AudioTagger.read(path)
                val artworks = audioFile.tag.artworks

                if (artworks.isNotEmpty()) {
                    val artwork = artworks.first()
                    return artwork.data
                }
            }
        } else {
            if (file.file.isFile()) {
                return Files.readAllBytes(file.file.toPath())
            }
        }
        return null
    }

    fun copy(): Song {
        val song = Song(this.sourceSongName, this.songName, this.genre, this.artist, this.album, this.coverArt)
        song.coverArtBytes = this.coverArtBytes
        return song
    }
}