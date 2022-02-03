package team.nexters.kida.ui.keyword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import team.nexters.kida.data.keyword.KeywordRepository
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
    repository: KeywordRepository
) : ViewModel() {

    val keywords = repository.getKeywords()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )
}
