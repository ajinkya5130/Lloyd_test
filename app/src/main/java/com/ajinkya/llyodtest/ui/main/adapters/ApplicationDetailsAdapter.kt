package com.ajinkya.llyodtest.ui.main.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajinkya.llyodtest.databinding.DayInfoLayoutBinding
import com.ajinkya.weather_forecast.model.WeatherItem

class WeatherListDetailsAdapter() :
    RecyclerView.Adapter<WeatherListDetailsAdapter.MyViewHolder>() {
    private var list = listOf<WeatherItem>()

    class MyViewHolder(val itemViewLayout: DayInfoLayoutBinding) :
        RecyclerView.ViewHolder(itemViewLayout.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DayInfoLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemViewLayout.model = list[position]


    }

    override fun getItemCount(): Int {
        return list.size;
    }

    fun addData(actionList: List<WeatherItem>) {
        list = actionList
        notifyDataSetChanged()

    }
}