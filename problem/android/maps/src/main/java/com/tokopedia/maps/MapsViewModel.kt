package com.tokopedia.maps

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokopedia.maps.api.Resource
import com.tokopedia.maps.model.ApiResponse
import com.tokopedia.maps.model.CountryDetail
import com.tokopedia.maps.repository.SearchRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class MapsViewModel(
        private val searchRepository: SearchRepository
) : ViewModel() {
    val responseMapsData : MutableLiveData<ApiResponse> = MutableLiveData()
    val responseCountryData : MutableLiveData<Resource<CountryDetail>> = MutableLiveData()


//    fun getFromPin()

    suspend fun getFromSearchBar(address: String) = viewModelScope.launch {
        val mapsData: Response<ApiResponse> = searchRepository.getCoordinate(address = address, language = "en")
        if (mapsData.isSuccessful){
            responseMapsData.postValue(mapsData.body())
        }
        responseCountryData.postValue(Resource.Loading())
        val countryData: Response<CountryDetail> = searchRepository.getCountryDetail(countryName = address)
        if (!countryData.isSuccessful){
            val newName = mapsData.body()?.results?.get(0)?.formatted_address
            Log.d("newData", newName)
            val newCountryData: Response<CountryDetail> = searchRepository.getCountryDetail(countryName = newName.toString())
            responseCountryData.postValue(handleSearchDataResponse(newCountryData))
        } else {
            responseCountryData.postValue(handleSearchDataResponse(countryData))
        }

        Log.d(" country query", countryData.body().toString())
    }

    private fun handleSearchDataResponse(response: Response<CountryDetail>) : Resource<CountryDetail>{
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }



}