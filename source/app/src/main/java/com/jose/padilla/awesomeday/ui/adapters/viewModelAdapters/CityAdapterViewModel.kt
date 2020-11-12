package com.jose.padilla.awesomeday.ui.adapters.viewModelAdapters

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jose.padilla.awesomeday.entities.MapDetail

class CityAdapterViewModel() : ViewModel() {
    val cityDetailLiveData = MutableLiveData<ArrayList<MapDetail>>()
    val location = MutableLiveData<Location>()


    fun addNewCity(mapDetail: MapDetail){
        if (cityDetailLiveData.value == null){
            cityDetailLiveData.value = ArrayList<MapDetail>()
        }
        cityDetailLiveData.value?.add(mapDetail)
        cityDetailLiveData.value = cityDetailLiveData.value
    }

}


