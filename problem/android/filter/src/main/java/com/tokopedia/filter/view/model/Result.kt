package com.tokopedia.filter.view.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("status")
    val status: Int
)