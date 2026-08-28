package music.manager.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import music.manager.enum.SongProperties
import music.manager.lib.PropertyHelpers
import music.manager.lib.PropertyHelpers.Companion.writeProperty

const val SOURCE_DIRECTORY_PROPERTY = "songsSourceDirectory"
const val OUTPUT_DIRECTORY_PROPERTY = "songsOutputDirectory"

data class SettingsPropertyEntry(
    val property: SongProperties,
    var enabled: Boolean = true
)

data class SettingsState(
    val modalVisible: Boolean = false,
    val properties: List<SettingsPropertyEntry> = arrayOf(
        SettingsPropertyEntry(SongProperties.ARTIST),
        SettingsPropertyEntry(SongProperties.ALBUM),
        SettingsPropertyEntry(SongProperties.GENRE),
    ).toList(),
    val sourceDirectory: String = PropertyHelpers.readProperty(SOURCE_DIRECTORY_PROPERTY),
    val outputDirectory: String = PropertyHelpers.readProperty(OUTPUT_DIRECTORY_PROPERTY),
)

class SettingsViewModel : ViewModel() {
    val settingsState: StateFlow<SettingsState>
        field = MutableStateFlow(SettingsState())

    fun open() {
        settingsState.update { it.copy(modalVisible = !settingsState.value.modalVisible) }
    }

    fun setProperties(list: List<SettingsPropertyEntry>) {
        settingsState.update { it.copy(properties = list) }
    }

    fun setEnabled(changeEntry: SettingsPropertyEntry) {
        val properties = settingsState.value.properties
        val newValue =
            MutableList<SettingsPropertyEntry>(
                properties.size,
                { SettingsPropertyEntry(SongProperties.ARTIST) }
            )
        val modifyIndex = properties.indexOf(changeEntry)

        for (index in newValue.indices) {
            val newProperty = SettingsPropertyEntry(properties[index].property, properties[index].enabled)
            if (index == modifyIndex) {
                newProperty.enabled = !newProperty.enabled
            }

            newValue[index] = newProperty
        }

        settingsState.update { it.copy(properties = newValue.toList()) }
    }

    fun setSourceDirectory(directory: String) {
        writeProperty("songsSourceDirectory", directory)
        settingsState.update { it.copy(sourceDirectory = directory) }
    }

    fun setOutputDirectory(directory: String) {
        writeProperty("songsOutputDirectory", directory)
        settingsState.update { it.copy(outputDirectory = directory) }
    }

}