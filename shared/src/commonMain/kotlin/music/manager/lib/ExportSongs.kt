package music.manager.lib

import io.github.vinceglb.filekit.path
import io.github.vinceglb.filekit.utils.toFile
import kotlinx.io.files.Path
import music.manager.classes.Song
import music.manager.classes.getProperty
import music.manager.enum.SettingsProperty
import music.manager.enum.SongProperties
import music.manager.viewmodels.SettingsPropertyEntry
import org.jaudiotagger.kt.AudioTagger
import org.jaudiotagger.kt.tag.Artwork
import org.jaudiotagger.kt.tag.FieldKey
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.LinkedList
import kotlin.io.path.pathString

fun exportSongs(songs: List<Song>, propertyOrder: List<SettingsPropertyEntry>) {
    val baseOutputDirectory = getProperty(SettingsProperty.OUTPUT_DIRECTORY_PROPERTY.propertyName)
    val baseSourceDirectory = getProperty(SettingsProperty.SOURCE_DIRECTORY_PROPERTY.propertyName)

    for (song in songs) {
        val sourceFile = File(baseSourceDirectory + "/${song.sourceSongName}")

        val subDirectoryPath = generateSubDirectoryPath(song, propertyOrder)

        generateSubDirectories(baseOutputDirectory, subDirectoryPath)

        val sourcePath = baseSourceDirectory + "/${song.sourceSongName}"
        val targetPath = baseOutputDirectory + subDirectoryPath + "/${song.songName}.${sourceFile.extension}"

        Files.copy(
            Paths.get(sourcePath),
            Paths.get(targetPath),
            StandardCopyOption.REPLACE_EXISTING
        )

        setTags(Path(targetPath), song)
    }
}

fun generateSubDirectories(baseDirectory: String, subDirectory: String) {
    Files.createDirectories(Paths.get(baseDirectory + subDirectory))
}

fun generateSubDirectoryPath(song: Song, propertyOrder: List<SettingsPropertyEntry>): String {
    var result = ""

    for ((property, enabled) in propertyOrder) {
        if (enabled) {
            val subDirectoryName: String = when (property) {
                SongProperties.GENRE -> song.genre
                SongProperties.SONG_NAME -> song.songName
                SongProperties.ARTIST -> song.artist
                SongProperties.ALBUM -> song.album
            }

            result += "/${subDirectoryName}"
        }
    }

    return result
}

fun setTags(path: Path, song: Song) {
    val file = AudioTagger.read(path)

    file.tag.set(FieldKey.ALBUM, song.album)

    if (song.coverArt != null) {
        file.tag.setArtwork(Artwork(song.coverArt!!.file.readBytes(), mimeType = song.coverArt!!.file.extension))
    }

    AudioTagger.write(path, file.tag)
}