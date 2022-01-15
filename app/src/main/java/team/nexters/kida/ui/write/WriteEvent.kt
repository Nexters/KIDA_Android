package team.nexters.kida.ui.write

sealed class WriteEvent {
    data class OnTitleChange(val title: String) : WriteEvent()
    data class OnContentChange(val content: String) : WriteEvent()
    data class OnKeywordChange(val keyword: String) : WriteEvent()
    object OnSaveDiary : WriteEvent()
}