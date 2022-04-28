package com.ajinkya.llyodtest.repository

import com.ajinkya.llyodtest.api_calls.ApiResponse
import com.ajinkya.llyodtest.model.WeatherModel

class FakeServerRepository : ServerRepositoryInterface {
    //    suspend fun getCityWeatherData(cityName: String) = apiServices.getWeatherJsonData(cityName)
    override suspend fun getCityWeatherData(cityName: String): ApiResponse<WeatherModel> {
        return ApiResponse.success(WeatherModel())
    }
}