package com.ajinkya.llyodtest.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ajinkya.llyodtest.MainCoroutineRule
import com.ajinkya.llyodtest.api_calls.Status
import com.ajinkya.llyodtest.getOrAwaitValueTest
import com.ajinkya.llyodtest.repository.FakeServerRepository
import com.ajinkya.llyodtest.ui.main.viewModel.MainViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        mainViewModel = MainViewModel(FakeServerRepository())
    }

    @Test
    fun `check weather city is available`() {
        mainViewModel.isWeatherCityNotNull("")

        val value = mainViewModel.isCityNull.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

}