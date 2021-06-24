package com.tokopedia.maps.api


import com.tokopedia.maps.model.CountryDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryApi {

    @GET("{country}?fullText=true")
    suspend fun searchCountry(
            @Path("country")
            country: String
    ) : Response<CountryDetail>
}