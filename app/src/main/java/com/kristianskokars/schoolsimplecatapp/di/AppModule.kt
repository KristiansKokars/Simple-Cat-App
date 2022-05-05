package com.kristianskokars.schoolsimplecatapp.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kristianskokars.schoolsimplecatapp.common.BASE_URL
import com.kristianskokars.schoolsimplecatapp.common.CAT_DATABASE
import com.kristianskokars.schoolsimplecatapp.data.data_source.local.CatDatabase
import com.kristianskokars.schoolsimplecatapp.data.data_source.remote.CatAPI
import com.kristianskokars.schoolsimplecatapp.data.repository.CatRepository
import com.kristianskokars.schoolsimplecatapp.data.repository.CatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCatRepository(
        catAPI: CatAPI,
        catDb: CatDatabase
    ): CatRepository = CatRepositoryImpl(catAPI, catDb)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideCatAPI(retrofit: Retrofit): CatAPI = retrofit.create(CatAPI::class.java)

    @Provides
    @Singleton
    fun provideCatDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room
            .databaseBuilder(
                context,
                CatDatabase::class.java,
                CAT_DATABASE
            ).fallbackToDestructiveMigration().build()
    }
}
