package team.nexters.kida.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.nexters.kida.data.diary.DiaryRepository
import team.nexters.kida.ui.Screen
import team.nexters.kida.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: DiaryRepository
) : ViewModel() {

    val diaries = repository.getDiaries()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            diaries.collectLatest {
                if (it.isEmpty()) {
                    sendUiEvent(UiEvent.Navigate(Screen.Keyword.route))
                }
            }
        }
    }

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.OnEditClick -> {
                sendUiEvent(UiEvent.Navigate(Screen.EditDialog.route + "?diaryId=${event.diaryId}"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
