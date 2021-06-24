package com.tokopedia.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokopedia.maps.repository.SearchRepository

class MapsViewModelProviderFactory(
        val SearchRepository: SearchRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MapsViewModel(SearchRepository) as T
    }
}