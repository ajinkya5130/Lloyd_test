package com.ajinkya.llyodtest.api_calls

import com.ajinkya.llyodtest.BuildConfig
import com.ajinkya.llyodtest.util.Constants
import com.ajinkya.weather_forecast.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrl.WEATHER_INFO_URL)
    suspend fun getWeatherJsonData(
        @Query("q") query: String,
        @Query("cnt") count: Int = 16,
        @Query("units") units: String = Constants.Units,
        @Query("appid") appID: String = BuildConfig.API_KEY
    ): Response<WeatherModel>
}