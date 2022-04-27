package com.ajinkya.llyodtest.repository

import com.ajinkya.llyodtest.api_calls.ApiService
import javax.inject.Inject


class ServerRepository @Inject constructor(private val apiServices: ApiService) {
    suspend fun getCityWeatherData(cityName: String) = apiServices.getWeatherJsonData(cityName)
}