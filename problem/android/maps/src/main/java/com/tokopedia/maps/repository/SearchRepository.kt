package com.tokopedia.maps.repository

import com.tokopedia.maps.api.CountryRetrofitInstance
import com.tokopedia.maps.api.MapsRetrofitInstance

class SearchRepository {
    suspend fun getCoordinate(address: String, language: String) =
            MapsRetrofitInstance.api.searchCoordinates(address, language)

    suspend fun getCountryDetail(countryName: String) =
            CountryRetrofitInstance.api.searchCountry(countryName)
}