package com.alecbrando.musicplayer.di

import com.alecbrando.musicplayer.data.remote.ApiService
import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.domain.repository.Repository
import com.alecbrando.musicplayer.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApisService(): ApiService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepositoryImpl(apiService: ApiService): Repository = RepositoryImpl(apiService)

}