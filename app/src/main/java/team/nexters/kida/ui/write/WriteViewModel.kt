package team.nexters.kida.ui.write

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.nexters.kida.data.diary.Diary
import team.nexters.kida.data.diary.DiaryRepository
import team.nexters.kida.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var diary by mutableStateOf<Diary?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set

    var keyword by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val diaryId = savedStateHandle.get<Int>("diaryId")!!
        if (diaryId != -1) {
            viewModelScope.launch {
                repository.getDiaryById(diaryId)?.let { diary ->
                    title = diary.title
                    content = diary.content
                    keyword = diary.keyword
                }
            }
        }
    }

    fun onEvent(event: WriteEvent) {
        when (event) {
            is WriteEvent.OnTitleChange -> {
                title = event.title
            }
            is WriteEvent.OnContentChange -> {
                content = event.content
            }
            is WriteEvent.OnKeywordChange -> {
                keyword = event.keyword
            }
            is WriteEvent.OnSaveDiary -> {
                viewModelScope.launch {
                    if (title.isBlank() || content.isBlank() || keyword.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(message = "좀 채워라~"))
                        return@launch
                    }
                    repository.insertDiary(
                        Diary(
                            title = title,
                            content = content,
                            keyword = keyword,
                            id = diary?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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
