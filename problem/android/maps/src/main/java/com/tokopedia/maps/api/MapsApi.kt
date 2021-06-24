package com.tokopedia.maps.api

import com.tokopedia.maps.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MapsApi {

    // Fungsi search koordinat
    // Request GET
    // Require Headers
    @Headers(
        "x-rapidapi-key:793fbcfd78msh332ebd5e7759c28p139d56jsn22647d305c50",
        "x-rapidapi-host:google-maps-geocoding.p.rapidapi.com",
        "useQueryString:true"
    )
    @GET("json?")
    suspend fun searchCoordinates(
            @Query("address")
            address: String,
            @Query("&language")
            language: String
    ) : Response<ApiResponse>


}