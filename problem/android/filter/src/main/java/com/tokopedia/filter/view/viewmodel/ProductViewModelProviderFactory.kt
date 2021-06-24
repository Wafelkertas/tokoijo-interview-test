package com.tokopedia.filter.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tokopedia.filter.view.repository.ProductRepository

class ProductViewModelProviderFactory(
        private val productRepository: ProductRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ProductViewmodel(productRepository) as T
}