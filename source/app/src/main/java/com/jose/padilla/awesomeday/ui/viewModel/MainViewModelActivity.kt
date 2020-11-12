package com.jose.padilla.awesomeday.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jose.padilla.awesomeday.entities.ForecastMap
import com.jose.padilla.awesomeday.repository.DataRepository
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception

class MainViewModelActivity : ViewModel(){

    val cityDetailLiveData = MutableLiveData<ArrayList<ForecastMap>>()
    private val repository = DataRepository()


    fun obtainListForecastData(list :ArrayList<String>){
        if (list.size == 0){
            cityDetailLiveData.value =cityDetailLiveData.value
            return;
        }
        GlobalScope.launch(Dispatchers.IO) {
            coroutineScope {

                val listForecast = ArrayList<ForecastMap>(list.size)
                val deferredList = ArrayList<Deferred<Response<ForecastMap>>>()
                try {
                    list.forEachIndexed { i, id ->
                         deferredList.add(async { repository.getForecastFiveDays(id) })
                    }
                    deferredList.forEachIndexed { i, _ ->
                            listForecast.add( deferredList[i].await().body()!!)
                    }
                    cityDetailLiveData.value = listForecast

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


    }

}