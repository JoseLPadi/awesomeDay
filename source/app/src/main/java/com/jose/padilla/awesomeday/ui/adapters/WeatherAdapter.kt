package com.jose.padilla.awesomeday.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jose.padilla.awesomeday.R
import com.jose.padilla.awesomeday.databinding.CellForecastBinding
import com.jose.padilla.awesomeday.entities.Weather

class WeatherAdapter(private val list: ArrayList<Weather>): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public val binding = CellForecastBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_forecast, parent,false);

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position.isOdd()){
            Log.d("aaaa","ssss")
        } else {
            Log.d("aaaa","eeeee")
        }

        val item = list[position]
        holder.binding.hourTime.text = item!!.weatherDetail.temp
        holder.binding.temp.text = "ST: "+ item.weatherDetail.feels_like
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

fun Int.isOdd():Boolean{
    return (this % 2 == 0)
}