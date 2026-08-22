package music.manager.lib

import io.github.vinceglb.filekit.utils.toFile
import music.manager.classes.Song
import music.manager.enum.SongProperties
import org.farng.mp3.MP3File
import org.farng.mp3.id3.AbstractID3v2
import org.farng.mp3.id3.ID3v2_2
import org.farng.mp3.id3.ID3v2_3
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.LinkedList
import kotlin.io.path.pathString

fun exportSongs(songs: List<Song>, propertyOrder: LinkedList<SongProperties>) {
    val baseOutputDirectory = PropertyHelpers.readProperty("songsOutputDirectory")
    val baseSourceDirectory = PropertyHelpers.readProperty("songsSourceDirectory")

    for (song in songs) {
        val sourceFile = File(baseSourceDirectory + "/${song.sourceSongName}")

        val subDirectoryPath = generateSubDirectoryPath(song, propertyOrder)

        generateSubDirectories(baseOutputDirectory, subDirectoryPath)

        val target = Paths.get(
            baseOutputDirectory + subDirectoryPath + "/${song.songName}.${sourceFile.extension}"
        )

        Files.copy(
            Paths.get(baseSourceDirectory + "/${song.sourceSongName}"),
            target,
            StandardCopyOption.REPLACE_EXISTING
        )

        setTags(target, song)
    }
}

fun generateSubDirectories(baseDirectory: String, subDirectory: String) {
    Files.createDirectories(Paths.get(baseDirectory + subDirectory))
}

fun generateSubDirectoryPath(song: Song, propertyOrder: LinkedList<SongProperties>): String {
    var result = ""

    for (property in propertyOrder) {
        val subDirectoryName: String = when (property) {
            SongProperties.GENRE -> song.genre
            SongProperties.SONG_NAME -> song.songName
            SongProperties.ARTIST -> song.artist
            SongProperties.ALBUM -> song.album
        }

        result += "/${subDirectoryName}"
    }

    return result
}

fun setTags(path: Path, song: Song) {
    when (File(path.pathString).extension) {
        "mp3" -> handleMP3(path, song)
    }
}

fun handleMP3(path: Path, song: Song) {
    val mp3File = MP3File(File(path.pathString))
    val id3v2_3 = ID3v2_3()

    id3v2_3.albumTitle = song.album
    id3v2_3.leadArtist = song.artist
    id3v2_3.songTitle = song.songName


    mp3File.iD3v2Tag = id3v2_3

    mp3File.save()
}