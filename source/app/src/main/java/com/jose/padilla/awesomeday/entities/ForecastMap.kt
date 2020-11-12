package com.jose.padilla.awesomeday.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastMap (@SerializedName("list") val listWeather: ArrayList<Weather>,
                        val city:City,
                        val cod:String): Parcelable{


}