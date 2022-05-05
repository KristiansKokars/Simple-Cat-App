package com.kristianskokars.schoolsimplecatapp.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kristianskokars.schoolsimplecatapp.common.CAT_TABLE_NAME
import com.kristianskokars.schoolsimplecatapp.data.model.Cat
import kotlinx.coroutines.flow.Flow

// Single Source of Truth
@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCats(cats: List<Cat>)

    @Query("DELETE FROM $CAT_TABLE_NAME WHERE id NOT IN (:catIds)")
    suspend fun clearCatsNotIn(catIds: List<String>)

    @Query("SELECT * FROM $CAT_TABLE_NAME")
    fun getCats(): Flow<List<Cat>>
}
