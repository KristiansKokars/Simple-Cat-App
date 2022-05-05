package com.kristianskokars.schoolsimplecatapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kristianskokars.schoolsimplecatapp.common.CAT_TABLE_NAME

@Entity(tableName = CAT_TABLE_NAME)
data class Cat(
    @PrimaryKey(autoGenerate = false) val id: String,
    val url: String,
)
