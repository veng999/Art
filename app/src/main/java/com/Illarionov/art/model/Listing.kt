package com.Illarionov.art.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val netWorkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val refresh: () -> Unit
//    val retry: () -> Unit
)