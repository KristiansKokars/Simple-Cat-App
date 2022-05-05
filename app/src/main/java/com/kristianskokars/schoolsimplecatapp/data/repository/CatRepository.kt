package com.kristianskokars.schoolsimplecatapp.data.repository

import androidx.room.withTransaction
import com.kristianskokars.schoolsimplecatapp.data.data_source.local.CatDatabase
import com.kristianskokars.schoolsimplecatapp.data.data_source.remote.CatAPI
import com.kristianskokars.schoolsimplecatapp.data.model.Cat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

interface CatRepository {
    val cats: Flow<List<Cat>>
    suspend fun refreshCats()
}

class CatRepositoryImpl(
    private val remote: CatAPI,
    private val local: CatDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CatRepository {
    private val catDao = local.catDao()
    override val cats = catDao.getCats().flowOn(ioDispatcher)

    override suspend fun refreshCats() = withContext(ioDispatcher) {
        val newCats = remote.getCats().map { it.toCat() }

        local.withTransaction {
            catDao.addCats(newCats)
            catDao.clearCatsNotIn(newCats.map { it.id })
        }
    }
}
