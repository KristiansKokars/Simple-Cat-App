package com.kristianskokars.schoolsimplecatapp.data.data_source.remote

import com.kristianskokars.schoolsimplecatapp.data.model.Cat
import kotlinx.serialization.Serializable

@Serializable
data class CatAPIModel(
    val id: String,
    val url: String,
) {
    fun toCat() = Cat(id, url)
}
