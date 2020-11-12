package com.jose.padilla.awesomeday.repository

import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jose.padilla.awesomeday.entities.ForecastMap
import com.jose.padilla.awesomeday.entities.MapDetail
import com.jose.padilla.awesomeday.repository.network.EndPointInterface
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File.separator

class DataRepository (){
    private val epInterface: EndPointInterface
    init {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient())

            .baseUrl(BASE_URI).addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        epInterface = retrofit.create(EndPointInterface::class.java)
    }

    private val pk = "9743df54e7b0938209639c1395df46a5"
    fun getCityDetail(cityName:String, liveData: MutableLiveData<MapDetail>){
        var connect = epInterface.getMapDetail(cityName, pk)
        connect.enqueue(object : Callback<MapDetail>{
            override fun onFailure(call: Call<MapDetail>, t: Throwable) {
                liveData.value = null
            }
            override fun onResponse(call: Call<MapDetail>, response: Response<MapDetail>) {
                liveData.value = response.body()

            }
        })
    }
    fun getCityDetailLatLon(lat: String, lon:String, liveData: MutableLiveData<MapDetail>){
        var connect = epInterface.getMapDetailWithLatLon(lat, lon, pk)
        connect.enqueue(object : Callback<MapDetail>{
            override fun onFailure(call: Call<MapDetail>, t: Throwable) {
                liveData.value = null
            }
            override fun onResponse(call: Call<MapDetail>, response: Response<MapDetail>) {
                liveData.value = response.body()

            }
        })
    }
    suspend fun getForecastFiveDays(cityID: String): Response<ForecastMap> = epInterface.getForecastFiveDays(cityID, pk)
/*Call<List<Task>> call = taskService.getTasks();
List<Task>> tasks = call.execute().body();  */
/*
        var call: Call<ForecastMap> = epInterface.getForecastFiveDays(cityID, pk)
        return call.execute()


       /* var connect = epInterface.getForecastFiveDays(cityIDs[0],pk)
        connect.enqueue(object : Callback<ForecastMap>{
            override fun onFailure(call: Call<ForecastMap>, t: Throwable) {
                liveData.value = null
            }
            override fun onResponse(call: Call<ForecastMap>, response: Response<ForecastMap>) {
                liveData.value = response.body()
            }
        })*/
    }
*/
    companion object {
        val BASE_URI =  "https://api.openweathermap.org/data/2.5/"
    }
}


