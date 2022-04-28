package com.ajinkya.llyodtest.repository

import com.ajinkya.llyodtest.api_calls.ApiResponse
import com.ajinkya.llyodtest.model.WeatherModel

interface ServerRepositoryInterface {
    suspend fun getCityWeatherData(cityName: String): ApiResponse<WeatherModel>
}