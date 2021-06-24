package com.tokopedia.maps.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    val results: List<AddressComponents>,
    val status: String
)
