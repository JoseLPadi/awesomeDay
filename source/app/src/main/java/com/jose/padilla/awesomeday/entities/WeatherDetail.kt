package com.jose.padilla.awesomeday.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * On web service is called main
 * Main information about weather**/
@Parcelize
data class WeatherDetail(val temp:String,val feels_like:String,val temp_min:String,val temp_max:String,val pressure:String,val humidity:String) : Parcelable{
}

/*
"main": {
    "temp": 17.8,
    "feels_like": 15.58,
    "temp_min": 17.8,
    "temp_max": 17.8,
    "pressure": 1020,
    "humidity": 77,
    "sea_level": 1020,
    "grnd_level": 925
},*/