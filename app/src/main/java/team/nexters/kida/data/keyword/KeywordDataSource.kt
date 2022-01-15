package team.nexters.kida.data.keyword

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class KeywordDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getKeywords(): Flow<List<Keyword>> {
        return flow {
            emit(getKeywordsFromAsset())
        }.flowOn(Dispatchers.IO)
    }

    private fun getKeywordsFromAsset(): List<Keyword> {
        return context.assets.open("keywords.txt").use {
            it.reader().readText()
        }
            .split(", ")
            .map { Keyword(it) }
    }
}
