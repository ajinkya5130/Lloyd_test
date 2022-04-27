package com.ajinkya.llyodtest.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.llyodtest.api_calls.ApiResponse
import com.ajinkya.llyodtest.model.WeatherModel
import com.ajinkya.llyodtest.repository.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ServerRepository) : ViewModel() {

    private var _getWeatherInfoByCity = MutableLiveData<ApiResponse<WeatherModel>>()
    val getWeatherInfoByCity: LiveData<ApiResponse<WeatherModel>>
        get() =
            _getWeatherInfoByCity

    fun getWeatherInfo(cityName: String) = viewModelScope.launch {
        _getWeatherInfoByCity.postValue(ApiResponse.loading(null))
        try {
            repository.getCityWeatherData(cityName)
                .let { response ->
                    if (response.isSuccessful) {
                        _getWeatherInfoByCity.postValue(ApiResponse.success(response.body()))
                    } else
                        _getWeatherInfoByCity.postValue(
                            ApiResponse.error(response.message(), null)
                        )
                }
        } catch (e: Exception) {
            _getWeatherInfoByCity.postValue(
                ApiResponse.error(e.message!!, null)
            )

        }
    }
}