package com.jose.padilla.awesomeday.ui.viewModel

import android.location.Location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jose.padilla.awesomeday.entities.MapDetail
import com.jose.padilla.awesomeday.repository.DataRepository

class ListCityViewModelFragment : ViewModel(){
    val cityDetailLiveData = MutableLiveData<MapDetail>()
    var cityIDs = ArrayList<String>()
    private val repository = DataRepository()
    private lateinit var ownLocation: Location

    fun searchMap(cityName: String){
        repository.getCityDetail(cityName, cityDetailLiveData)
    }
    private fun searchMapWithLatLon(lat: String, lon:String){
        repository.getCityDetailLatLon(lat, lon, cityDetailLiveData)
    }
    fun setLastLocation(loc: Location){
        if (!this::ownLocation.isInitialized ) {
            searchMapWithLatLon(loc.latitude.toString(), loc.longitude.toString())
            ownLocation = loc
        }
    }

    fun updateListID(list: ArrayList<MapDetail>){
        cityIDs.clear()
        for(mapDetail in  list){
            cityIDs.add(mapDetail.id)
        }
    }
}

