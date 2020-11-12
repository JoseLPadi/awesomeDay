package com.jose.padilla.awesomeday.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind (val speed:String,val deg :String): Parcelable{
}



/*    "wind": {
        "speed": 4.84,
        "deg": 121
    },*/