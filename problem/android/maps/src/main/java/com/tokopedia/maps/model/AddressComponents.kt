package com.tokopedia.maps.model

data class AddressComponents(
        val address_components: List<GeographicLevel>,
        val formatted_address: String,
        val geometry: Geometry
)