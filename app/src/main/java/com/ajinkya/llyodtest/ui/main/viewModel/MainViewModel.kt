package com.ajinkya.llyodtest.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.llyodtest.api_calls.ApiResponse
import com.ajinkya.llyodtest.model.WeatherModel
import com.ajinkya.llyodtest.repository.ServerRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ServerRepositoryInterface) :
    ViewModel() {

    private var _getWeatherInfoByCity = MutableLiveData<ApiResponse<WeatherModel>>()
    val getWeatherInfoByCity: LiveData<ApiResponse<WeatherModel>>
        get() =
            _getWeatherInfoByCity
    private var _isCityNull = MutableLiveData<ApiResponse<String>>()
    val isCityNull: LiveData<ApiResponse<String>>
        get() =
            _isCityNull

    fun isWeatherCityNotNull(cityName: String): Boolean {
        return if (cityName.isNotEmpty()) {
            _isCityNull.postValue(ApiResponse.success(cityName))
            true
        } else {
            _isCityNull.postValue(ApiResponse.error("", ""))
            false
        }

    }

    fun getWeatherInfo(cityName: String) = viewModelScope.launch {
        if (isWeatherCityNotNull(cityName)) {
            _getWeatherInfoByCity.postValue(ApiResponse.loading(null))
            try {
                repository.getCityWeatherData(cityName)
                    .let { response ->
                        _getWeatherInfoByCity.postValue(response)
                    }
            } catch (e: Exception) {
                _getWeatherInfoByCity.postValue(
                    ApiResponse.error(e.message!!, null)
                )
            }
        } else {
            _getWeatherInfoByCity.postValue(
                ApiResponse.error("Please enter city", null)
            )
        }

    }
}