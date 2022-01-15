package team.nexters.kida.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.nexters.kida.data.diary.DiaryRepository
import team.nexters.kida.util.Routes
import team.nexters.kida.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {

    val diaries = repository.getDiaries()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.OnDiaryClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.DETAIL + "?diaryId=${event.diary.id}"))
            }
            is ListEvent.OnWriteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.WRITE))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}