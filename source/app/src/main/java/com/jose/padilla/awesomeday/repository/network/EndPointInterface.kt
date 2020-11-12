package com.jose.padilla.awesomeday.repository.network

import com.jose.padilla.awesomeday.entities.ForecastMap
import com.jose.padilla.awesomeday.entities.MapDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointInterface {

    @GET("weather")
    fun getMapDetail(@Query("q") city:String, @Query("appid") key:String): Call<MapDetail>

    @GET("weather")
    fun getMapDetailWithLatLon(@Query("lat") lat:String, @Query("lon") lon:String, @Query("appid") key:String): Call<MapDetail>


    @GET("forecast")
    suspend  fun getForecastFiveDays(@Query("id") cityID:String, @Query("appid") key:String): Response<ForecastMap>

}