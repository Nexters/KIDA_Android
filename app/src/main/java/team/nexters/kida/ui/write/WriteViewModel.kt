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
import team.nexters.kida.ui.Screen
import team.nexters.kida.util.UiEvent
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var diary by mutableStateOf<Diary?>(null)
        private set

    val date by mutableStateOf(Date())

    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set

    var keyword by mutableStateOf("")
        private set

    var preTitle = ""
    var preContent = ""

    var isWriteMode by mutableStateOf(false)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val diaryId = savedStateHandle.get<Int>("diaryId")!!
        if (diaryId != -1) {
            viewModelScope.launch {
                repository.getDiaryById(diaryId)?.let { diary ->
                    title = diary.title
                    preTitle = diary.title
                    content = diary.content
                    preContent = diary.content
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
                isWriteMode = true
            }
            is WriteEvent.OnSaveDiary -> {
                viewModelScope.launch {
                    repository.insertDiary(
                        Diary(
                            date = date,
                            title = title,
                            content = content,
                            keyword = keyword,
                            id = diary?.id ?: 0L
                        )
                    )
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
