package team.nexters.kida.ui.list.dialog

import androidx.lifecycle.SavedStateHandle
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
class ListDialogViewModel @Inject constructor(
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val diaryId = savedStateHandle.get<Long>("diaryId")!!

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListDialogEvent) {
        when (event) {
            is ListDialogEvent.OnClickDelete -> {
                viewModelScope.launch {
                    repository.deleteDiaryById(diaryId)
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
            is ListDialogEvent.OnClickModify -> {
                sendUiEvent(UiEvent.Navigate(Screen.Write.route + "?diaryId=$diaryId"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}