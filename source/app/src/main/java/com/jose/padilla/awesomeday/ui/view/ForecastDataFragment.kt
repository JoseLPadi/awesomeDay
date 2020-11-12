package com.jose.padilla.awesomeday.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jose.padilla.awesomeday.databinding.FragmentForecastDataBinding
import com.jose.padilla.awesomeday.ui.adapters.WeatherAdapter
import com.jose.padilla.awesomeday.ui.viewModel.ForecastDataViewModelFragment

class ForecastDataFragment : Fragment() {
    private lateinit var binding : FragmentForecastDataBinding
    private lateinit var adapter: WeatherAdapter

    private val viewModel: ForecastDataViewModelFragment by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForecastDataBinding.inflate(layoutInflater)

        arguments?.let { viewModel.listItems = it.getParcelable("forecast")!!

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WeatherAdapter(viewModel.listItems.listWeather)


        binding.listForecastcity1.adapter = adapter
        binding.listForecastcity1.layoutManager = LinearLayoutManager(context)
    }
}