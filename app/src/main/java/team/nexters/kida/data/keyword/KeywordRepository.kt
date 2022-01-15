package team.nexters.kida.data.keyword

import kotlinx.coroutines.flow.Flow

interface KeywordRepository {
    fun getKeywords(): Flow<List<Keyword>>
}
