package com.tokopedia.maps.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            Retrofit.Builder()
                    .baseUrl("https://restcountries.eu/rest/v2/name/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }
        val api by lazy{
            retrofit.create(CountryApi::class.java)
        }
    }
}