package music.manager.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ErrorState(
    var outputDirectory: Boolean = false
)

class ErrorViewModel : ViewModel() {
    val errorState: StateFlow<ErrorState>
        field = MutableStateFlow(ErrorState())

    fun toggleOutputDirectory() {
        errorState.update { it.copy(outputDirectory = !it.outputDirectory) }
    }
}