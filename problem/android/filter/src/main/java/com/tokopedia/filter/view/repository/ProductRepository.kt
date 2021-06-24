package com.tokopedia.filter.view.repository

import com.tokopedia.filter.view.api.ApiInstance
import com.tokopedia.filter.view.model.Result
import retrofit2.Response

class ProductRepository {
    suspend fun getListProduct() : Response<Result> = ApiInstance.api.getData()
}