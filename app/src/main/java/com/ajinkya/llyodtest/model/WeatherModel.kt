package com.ajinkya.llyodtest.model

import com.ajinkya.weather_forecast.model.WeatherItem

data class WeatherModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Double
)