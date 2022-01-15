package team.nexters.kida.ui.keyword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.nexters.kida.data.keyword.KeywordRepository
import team.nexters.kida.util.Routes
import team.nexters.kida.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val repository: KeywordRepository
) : ViewModel() {

    val keywords = repository.getKeywords()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: KeywordEvent) {
        when (event) {
            is KeywordEvent.OnKeywordClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.LIST))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
