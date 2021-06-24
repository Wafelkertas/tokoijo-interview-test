package com.tokopedia.maps.model


import com.google.gson.annotations.SerializedName

data class CountryDetailItem(
    @SerializedName("alpha2Code")
    val alpha2Code: String,
    @SerializedName("alpha3Code")
    val alpha3Code: String,
    @SerializedName("altSpellings")
    val altSpellings: List<Any>,
    @SerializedName("area")
    val area: Int,
    @SerializedName("callingCodes")
    val callingCodes: List<Any>,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("cioc")
    val cioc: String,
    @SerializedName("currencies")
    val currencies: List<Currency>,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nativeName")
    val nativeName: String,
    @SerializedName("numericCode")
    val numericCode: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("region")
    val region: String,
    @SerializedName("subregion")
    val subregion: String


)