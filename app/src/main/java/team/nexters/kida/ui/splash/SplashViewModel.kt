package team.nexters.kida.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.nexters.kida.data.diary.DiaryRepository
import team.nexters.kida.ui.Screen
import team.nexters.kida.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            diaryRepository.canWriteDiary()
                .collectLatest { canWriteDiary ->
                    delay(1000)
                    if (canWriteDiary) {
                        sendUiEvent(UiEvent.Navigate(Screen.Keyword.route))
                    } else {
                        sendUiEvent(UiEvent.Navigate(Screen.List.route))
                    }
                }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
