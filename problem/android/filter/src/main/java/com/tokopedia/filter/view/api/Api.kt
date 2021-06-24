package com.tokopedia.filter.view.api

import com.tokopedia.filter.view.model.Result
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("api/products")
    suspend fun getData(): Response<Result>

}