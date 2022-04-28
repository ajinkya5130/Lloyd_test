package com.ajinkya.llyodtest.model

data class City(
    val coord: Coord = Coord(),
    val country: String = "",
    val id: Int = 0,
    val name: String = "",
    val population: Int = 0,
    val timezone: Int = 0
)