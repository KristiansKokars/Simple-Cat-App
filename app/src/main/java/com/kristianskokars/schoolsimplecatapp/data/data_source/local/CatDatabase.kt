package com.kristianskokars.schoolsimplecatapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kristianskokars.schoolsimplecatapp.data.model.Cat

@Database(entities = [Cat::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}
