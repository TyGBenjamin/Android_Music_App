package com.alecbrando.musicplayer.paging3mediator

import androidx.paging.PagedList
import androidx.paging.PagingSource

class LivePagedListBuilder<Key : Any, Value : Any>(
    pagingSourceFactory: () -> PagingSource<Key, Value>,
    config: PagedList.Config
)