package com.jose.padilla.awesomeday.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jose.padilla.awesomeday.R
import com.jose.padilla.awesomeday.databinding.ActivityMainBinding
import com.jose.padilla.awesomeday.databinding.FragmentListCityBinding
import com.jose.padilla.awesomeday.ui.viewModel.ListCityViewModelFragment
import com.jose.padilla.awesomeday.ui.viewModel.MainViewModelActivity

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModelActivity by viewModels()

    lateinit var listCityViewModelFragment: ListCityViewModelFragment
    lateinit var mainViewModelActivity: MainViewModelActivity
    private lateinit var binding : ActivityMainBinding
    lateinit var alert: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, ListCityFragment()).addToBackStack(null).commit()
        }

        listCityViewModelFragment= ViewModelProvider(this).get(ListCityViewModelFragment::class.java)


        binding.showForecast.setOnClickListener {
            val builder =AlertDialog.Builder(this).setTitle(title).setMessage("Loading forecast data").setCancelable(false)
            Snackbar.make(binding.root, "Error loading City.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            alert = builder.create()
            alert.show()
            viewModel.obtainListForecastData(listCityViewModelFragment.cityIDs)


            /*

            Muestro alert
            obtengo el listado de foreccast
            lo agrego al fragmento como extra
             Abro el fragmento

            */


        }
        createObservers()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun createObservers(){

        viewModel.cityDetailLiveData.observe(this, Observer {
            if (viewModel.cityDetailLiveData.value != null ) {
                alert.dismiss()
                val newFrag = ForecastDataFragment()
                val arguments = Bundle()
               // arguments.putParcelable("forecast", viewModel.cityDetailLiveData.value)

                newFrag.arguments = arguments


                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, newFrag, null).addToBackStack(null)
                    .commit()

            } else {
                if (alert.isShowing){
                    alert.dismiss()

                    Snackbar.make(binding.root, "Error loading City.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                }
            }

        })

       /*adapterViewModel.cityDetailLiveData.observe( viewLifecycleOwner, Observer{
            viewModel.updateListID(it)
        })*/
    }




}