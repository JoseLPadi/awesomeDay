package com.jose.padilla.awesomeday.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.jose.padilla.awesomeday.R
import com.jose.padilla.awesomeday.databinding.FragmentListCityBinding
import com.jose.padilla.awesomeday.ui.adapters.CityAdapter
import com.jose.padilla.awesomeday.ui.adapters.viewModelAdapters.CityAdapterViewModel
import com.jose.padilla.awesomeday.ui.viewModel.ListCityViewModelFragment


class ListCityFragment : Fragment() {
    private lateinit var binding : FragmentListCityBinding
    private lateinit var dialog:Dialog
    private lateinit var adapter: CityAdapter
    private  lateinit var fusedLocationClient: FusedLocationProviderClient


    private val viewModel: ListCityViewModelFragment by activityViewModels()
    private val adapterViewModel: CityAdapterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCityBinding.inflate(layoutInflater, container, false)

        adapter = CityAdapter(adapterViewModel)
        binding.listMapSelected.layoutManager = LinearLayoutManager(context)

/*
        val mLayoutManager = LinearLayoutManager(context, )
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.listMapSelected.layoutManager = mLayoutManager;
*/

        binding.listMapSelected.adapter = adapter
         fusedLocationClient = context?.let {
             LocationServices.getFusedLocationProviderClient(it) }!!


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createObservers()
        context?.let { initializeDialog(it)}

        binding.addCity.setOnClickListener {
            if (adapter.itemCount<5) {
                dialog.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                dialog.findViewById<Button>(R.id.buttonCancel).isEnabled = true;
                //   dialog.findViewById<EditText>(R.id.editTextCity).text.clear()
                dialog.show()
            } else
                Snackbar.make(it, "You have five maps added", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }

    }

    private fun createObservers(){

        viewModel.cityDetailLiveData.observe(viewLifecycleOwner, Observer {
            if (viewModel.cityDetailLiveData.value != null) {
                dialog.dismiss()
                adapter.addCity(it)
                viewModel.cityDetailLiveData.value = null
            } else {
                if (dialog.isShowing) {
                    dialog.dismiss()
                    view?.let { it1 ->
                        Snackbar.make(it1, "Error loading City.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
            }

        })

        adapterViewModel.cityDetailLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.updateListID(it)
        })
    }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 201

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                } else {
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            if (location != null) {
                                viewModel.setLastLocation(location)
                            }
                        }
                }
            }
        }
    }
     private fun checkPermission() {
         context?.let {
             if (ContextCompat.checkSelfPermission(
                     it,
                     Manifest.permission.ACCESS_COARSE_LOCATION
                 ) == PERMISSION_GRANTED
             ) {
                 fusedLocationClient.lastLocation
                     .addOnSuccessListener { location: Location? ->
                         if (location != null) {
                             viewModel.setLastLocation(location)
                         }
                     }
             } else {
                 if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ) {
                     view?.let { it1 ->
                         Snackbar.make(
                             it1,
                             "In order to add your location, you should accept the permission.",
                             Snackbar.LENGTH_LONG
                         )
                             .setAction("Action", null).show()
                     }
                 } else {
                     startLocationPermissionRequest()
                 }
             }
         }
     }
         private fun startLocationPermissionRequest(){

        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun initializeDialog(context: Context) {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.alert_add_new_city)
        dialog.setCancelable(false)

        dialog.findViewById<Button>(R.id.buttonAddCity).setOnClickListener {
            dialog.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            dialog.findViewById<Button>(R.id.buttonCancel).isEnabled = false;
            viewModel.searchMap(dialog.findViewById<EditText>(R.id.editTextCity).text.toString())

            Snackbar.make(it, "Loading city", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        dialog.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            adapter.notifyDataSetChanged()
            dialog.dismiss()

        }


    }



}