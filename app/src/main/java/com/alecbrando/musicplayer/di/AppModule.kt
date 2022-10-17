package com.alecbrando.musicplayer.di

import android.content.Context
import androidx.room.Room
import com.alecbrando.musicplayer.data.local.SongsDatabase
import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.data.service.ApiService
import com.alecbrando.musicplayer.domains.datastore.DataStoreRepository
import com.alecbrando.musicplayer.domains.datastore.DataStoreRepositoryImpl
import com.alecbrando.musicplayer.domains.repository.Repository
import com.alecbrando.musicplayer.domains.usecases.GetAllSongsUseCase
import com.alecbrando.musicplayer.utils.objects.SongsUrl.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

//    @Singleton
//    @Provides
//    fun provideRepo(apiService: ApiService): Repository = RepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context): DataStoreRepository{
        return DataStoreRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext applicationContext: Context): SongsDatabase = Room.databaseBuilder(
        applicationContext,
        SongsDatabase::class.java, "songs-database"
    ).build()

    @Singleton
    @Provides
    fun providesRepo(apiService: ApiService, songsDatabase: SongsDatabase) : Repository{
        return RepositoryImpl(apiService, songsDatabase)
    }

    @Singleton
    @Provides
    fun providesUsecase(repository: Repository) : GetAllSongsUseCase {
        return GetAllSongsUseCase(repository)
    }
}