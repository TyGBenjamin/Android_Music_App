package com.alecbrando.musicplayer.paging3mediator

import androidx.datastore.preferences.PreferencesProto.Value
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.alecbrando.musicplayer.data.local.SongsDao
import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.domains.models.Songs
import java.security.Key
import java.util.concurrent.Flow

@ExperimentalPagingApi
abstract class MusicRemoteMediator(
): RemoteMediator<Key, Value>(){

}