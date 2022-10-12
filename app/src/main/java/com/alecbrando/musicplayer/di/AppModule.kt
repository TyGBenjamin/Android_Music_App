package com.alecbrando.musicplayer.di

import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.data.service.ApiService
import com.alecbrando.musicplayer.domains.repository.Repository
import com.alecbrando.musicplayer.utils.SongsUrl.BASE_URL
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
    fun provideApiService(): ApiService{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(apiService: ApiService): Repository = RepositoryImpl(apiService)
}