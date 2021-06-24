package com.tokopedia.filter.view.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokopedia.filter.view.model.Product
import com.tokopedia.filter.view.model.Result
import com.tokopedia.filter.view.repository.ProductRepository
import com.tokopedia.filter.view.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewmodel(
        private val productRepository: ProductRepository
) : ViewModel() {
    private val resultData: MutableLiveData<List<Product>> = MutableLiveData()
    val observableData: MutableLiveData<List<Product>> = MutableLiveData()
    val listOfLocation: MutableLiveData<List<String>> = MutableLiveData()
    val listOfPrice: MutableLiveData<List<Int>> = MutableLiveData()

    suspend fun getProductData() = viewModelScope.launch {
        val response = productRepository.getListProduct()
        Log.d("response", response.body()?.products.toString())
        when (val productData = errorHandler(response)) {
            is Resource.Success -> {
                val spreadData: ArrayList<Product> = ArrayList()
                val spreadLokasi: ArrayList<String> = ArrayList()
                val spreadHarga: ArrayList<Int> = ArrayList()

                for (item in productData.data?.products!!) {
                    spreadData.add(item)
                    spreadLokasi.add(item.lokasi)
                    spreadHarga.add(item.harga)
                }
                Log.d("response array", spreadData.toString())
                resultData.postValue(spreadData)
                listOfLocation.postValue(sortListOfLocation(spreadLokasi))
                listOfPrice.postValue(sortOfPrices(spreadHarga))
                observableData.postValue(spreadData)
            }
            is Resource.Error -> {
                resultData.postValue(null)
                response.let { message ->
                    Log.e("error", "an error occured: $message")
                }
            }
            is Resource.Loading -> {
                resultData.postValue(null)
                response.let { message ->
                    Log.e("loading", "an error occured: $message")
                }
            }
        }
    }

    private fun errorHandler(response: Response<Result>): Resource<Result> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun sortListOfLocation(locationArray: ArrayList<String>): List<String> {
        val grouped = locationArray.groupingBy { it }.eachCount()
        val reversed = grouped.toSortedMap(Comparator.reverseOrder())
        return reversed.keys.toList()
    }

    private fun sortOfPrices(priceArray: ArrayList<Int>): List<Int> {
        return priceArray.sorted()
    }

    fun sortingViewmodel(lokasi: List<String>, hargaAwal: Int?, hargaAkhir: Int?) {
        val newData = resultData.value!!.filter { data ->
            if (lokasi.isEmpty()) {
                val lokasiEmpty: List<String> = listOfLocation.value!!
                lokasiEmpty.contains(data.lokasi)
            } else {
                lokasi.contains(data.lokasi)
            }
        }.filter {
            it.harga in hargaAwal!!..hargaAkhir!!
        }
        observableData.postValue(newData)

    }

}