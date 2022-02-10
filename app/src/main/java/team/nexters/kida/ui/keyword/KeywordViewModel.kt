package team.nexters.kida.ui.keyword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import team.nexters.kida.data.diary.DiaryRepository
import team.nexters.kida.data.keyword.KeywordRepository
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    repository: KeywordRepository,
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    val keywords = repository.getKeywords()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )

    val canWriteDiary: StateFlow<Boolean>
        get() = diaryRepository.canWriteDiary().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )
}
