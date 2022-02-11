package team.nexters.kida.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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

    fun onEvent(event: ListEvent, diaryId : Long = -1) {
        when (event) {
            is ListEvent.OnModifyClick -> {
                // TODO 다이어로그 띄우는 방식으로 수정
                sendUiEvent(UiEvent.Navigate(Screen.Write.route + "?diaryId=${diaryId}&keyword={keyword}"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
