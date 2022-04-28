package com.ajinkya.llyodtest.model

import com.ajinkya.weather_forecast.model.WeatherItem

data class WeatherModel(
    val city: City = City(),
    val cnt: Int = 0,
    val cod: String = "",
    val list: List<WeatherItem> = listOf(),
    val message: Double = 0.0
)