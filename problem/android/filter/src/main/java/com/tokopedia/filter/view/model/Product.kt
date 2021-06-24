package com.tokopedia.filter.view.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("lokasi")
    val lokasi: String,
    @SerializedName("product")
    val product: String
)