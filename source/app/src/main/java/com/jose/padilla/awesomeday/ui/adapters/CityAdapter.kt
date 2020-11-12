package com.jose.padilla.awesomeday.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jose.padilla.awesomeday.R
import com.jose.padilla.awesomeday.databinding.CellCityBinding
import com.jose.padilla.awesomeday.entities.MapDetail
import com.jose.padilla.awesomeday.ui.adapters.viewModelAdapters.CityAdapterViewModel
import com.squareup.picasso.Picasso


class CityAdapter (private val model: CityAdapterViewModel): RecyclerView.Adapter<CityAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_city, parent,false);

        return ViewHolder(view)

    }


    override fun getItemCount(): Int {
        if (model.cityDetailLiveData.value == null) {
            return 0
        } else
            return model.cityDetailLiveData.value!!.size
    }

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
        val item = model.cityDetailLiveData.value?.get(position)
        holder.binding.cityName.text = item!!.name
        holder.binding.feelsLike.text = "ST: "+ item.weatherDetail.feels_like
        holder.binding.minMax.text = "Min: ${item.weatherDetail.temp_min} Max: ${item.weatherDetail.temp_max}"
        holder.binding.weatherDescription.text= item.weatherDescription[0].description
        holder.binding.iconWeather.loadImage(item.weatherDescription[0].buildIconIRL())
        holder.binding.deleteCity.setOnClickListener(View.OnClickListener {
            model.cityDetailLiveData.value?.removeAt(position)
            notifyDataSetChanged()
        })
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public val binding = CellCityBinding.bind(view)

    }

    fun addCity(mapDetail: MapDetail){
        model.addNewCity(mapDetail)
        notifyDataSetChanged()
    }

}

fun ImageView.loadImage(imgURL:String){
    Picasso.get().load(imgURL).into(this)
}
