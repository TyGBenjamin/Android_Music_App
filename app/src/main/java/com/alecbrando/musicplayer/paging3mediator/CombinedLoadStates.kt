package com.alecbrando.musicplayer.paging3mediator

import androidx.paging.LoadState
import androidx.paging.LoadStates

class CombinedLoadStates(
    refresh: LoadState,
    prepend: LoadState,
    append: LoadState,
    source: LoadStates,
    mediator: LoadStates? = null
)