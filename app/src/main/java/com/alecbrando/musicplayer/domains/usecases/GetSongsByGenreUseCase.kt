package com.alecbrando.musicplayer.domains.usecases

import com.alecbrando.musicplayer.domains.models.SongsWrapper
import com.alecbrando.musicplayer.domains.repository.Repository
import com.alecbrando.musicplayer.resources.Resource
import javax.inject.Inject

class GetSongsByGenreUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(genre: String): Resource<SongsWrapper> = repository.getSongsByGenres(genre)
}