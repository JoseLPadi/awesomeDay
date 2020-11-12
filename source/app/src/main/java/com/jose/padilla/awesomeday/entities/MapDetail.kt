package com.jose.padilla.awesomeday.entities

import com.google.gson.annotations.SerializedName

data class MapDetail(val name:String,
                     val id:String,
                     @SerializedName("weather") val weatherDescription: List<WeatherDescription>,
                     @SerializedName("main") val weatherDetail: WeatherDetail,
                     val wind: Wind,
                     val cod:String,
val message:String) {




}