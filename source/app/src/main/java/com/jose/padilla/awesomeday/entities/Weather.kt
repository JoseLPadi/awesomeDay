package com.jose.padilla.awesomeday.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(@SerializedName("weather") val weatherDescription:List<WeatherDescription>,
                    @SerializedName("main") val weatherDetail: WeatherDetail,
                    @SerializedName("dt_txt") val dateTXT: String,
                    val wind: Wind) : Parcelable{


}


/*conjunto de cosas*/