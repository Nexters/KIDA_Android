package team.nexters.kida.data.keyword

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val dataSource: KeywordDataSource
) : KeywordRepository {
    override fun getKeywords(): Flow<List<Keyword>> {
        return dataSource.getKeywords()
    }
}
