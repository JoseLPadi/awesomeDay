package com.jose.padilla.awesomeday.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * On web service is called weather
 * detail about weather on text
 **/
@Parcelize
data class WeatherDescription(val id:String,val main:String,val description:String,val icon:String): Parcelable{


    public fun buildIconIRL():String{
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }

    companion object {
        const val BASE_IMG_URI =  "https://api.openweathermap.org/data/2.5/"
    }
}
/*

"weather": [
{
    "id": 804,
    "main": "Clouds",
    "description": "overcast clouds",
    "icon": "04n"
}
],

        */