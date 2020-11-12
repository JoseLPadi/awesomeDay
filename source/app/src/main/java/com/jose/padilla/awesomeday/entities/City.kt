package com.jose.padilla.awesomeday.entities

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
open class City(val id:String,
                val name :String,
                val country:String): Parcelable{
}
