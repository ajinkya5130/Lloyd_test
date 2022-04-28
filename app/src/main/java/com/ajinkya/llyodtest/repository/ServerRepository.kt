package com.ajinkya.llyodtest.repository

import com.ajinkya.llyodtest.api_calls.ApiResponse
import com.ajinkya.llyodtest.api_calls.ApiService
import com.ajinkya.llyodtest.model.WeatherModel
import javax.inject.Inject


class ServerRepository @Inject constructor(private val apiServices: ApiService) :
    ServerRepositoryInterface {
    override suspend fun getCityWeatherData(cityName: String): ApiResponse<WeatherModel> {
        try {
            val apiResponse = apiServices.getWeatherJsonData(cityName)
            apiResponse.let { response ->
                return if (response.isSuccessful) {
                    ApiResponse.success(response.body())
                } else
                    ApiResponse.error(response.message(), null)
            }
        } catch (e: Exception) {
            return ApiResponse.error(e.message!!, null)
        }
    }
}