package com.ajinkya.llyodtest.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajinkya.llyodtest.R
import com.ajinkya.llyodtest.api_calls.Status
import com.ajinkya.llyodtest.base.BaseActivity
import com.ajinkya.llyodtest.databinding.ActivityMainBinding
import com.ajinkya.llyodtest.ui.main.adapters.WeatherListDetailsAdapter
import com.ajinkya.llyodtest.ui.main.viewModel.MainViewModel
import com.ajinkya.llyodtest.util.Constants


class MainActivity : BaseActivity() {

    private lateinit var searchView: SearchView
    private var localStrCityName = Constants.cityName
    private lateinit var rvLayoutManager: LinearLayoutManager
    private lateinit var weatherListDetailsAdapter: WeatherListDetailsAdapter
    private val TAG = "MainActivity"
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.materialToolbarMain)
        binding.materialToolbarMain.title = localStrCityName
        weatherListDetailsAdapter = WeatherListDetailsAdapter()
        rvLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWeather.apply {
            setHasFixedSize(true)
            adapter = weatherListDetailsAdapter
            layoutManager = rvLayoutManager
        }
        viewModel.getWeatherInfo(localStrCityName)
        liveDataInformation()

    }

    private fun liveDataInformation() {
        viewModel.getWeatherInfoByCity.observe(this) { response ->
            try {
                when (response.status) {
                    Status.SUCCESS -> {
                        binding.materialToolbarMain.title = localStrCityName
                        binding.model = response.data
                        weatherListDetailsAdapter.addData(response.data!!.list)
                        Log.e(TAG, "liveDataInformation: ${response.data}")
                        progressDialog.hideProgress()

                    }
                    Status.ERROR -> {
                        Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                        progressDialog.hideProgress()
                    }
                    Status.LOADING -> {
                        progressDialog.showProgress(
                            this,
                            "$localStrCityName Weather Info is loading...",
                            false
                        )
                    }

                }
            } catch (e: Exception) {
                Log.e(TAG, "liveDataInformation: ${e.message}")
                progressDialog.hideProgress()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem: MenuItem = menu.findItem(R.id.menu_search)
        searchView = menuItem.actionView as SearchView
        val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setTextColor(Color.WHITE)
        searchView.apply {
            queryHint = "Type city name here"
            setOnQueryTextListener(search)
            maxWidth = Int.MAX_VALUE
        }

        return super.onCreateOptionsMenu(menu)

    }

    private val search = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {

            if (query != null) {
                searchView.onActionViewCollapsed()
                localStrCityName = query
                Log.e(TAG, "onQueryTextSubmit: $query")
                viewModel.getWeatherInfo(query)
            } else {
                Log.e(TAG, "null onQueryTextSubmit: $query")
                viewModel.getWeatherInfo(Constants.cityName)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

    }
}