package com.tokopedia.maps

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tokopedia.maps.repository.SearchRepository
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.tokopedia.maps.api.Resource
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*


open class MapsActivity : AppCompatActivity() {

    private var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null

    private lateinit var mapsViewModel: MapsViewModel
    private lateinit var textCountryName: TextView
    private lateinit var textCountryCapital: TextView
    private lateinit var textCountryPopulation: TextView
    private lateinit var textCountryCallCode: TextView
    private lateinit var textCountryNameData: TextView
    private lateinit var textCountryCapitalData: TextView
    private lateinit var textCountryPopulationData: TextView
    private lateinit var textCountryCallCodeData: TextView

    private var editText: EditText? = null
    private var buttonSubmit: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        bindViews()
        initListeners()
        loadMap()
        val searchRepository = SearchRepository()
        val mapsViewModelProviderFactory = MapsViewModelProviderFactory(searchRepository)
        mapsViewModel = ViewModelProvider(this, mapsViewModelProviderFactory).get(MapsViewModel::class.java)
        observeData()



}

    private fun bindViews() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        editText = findViewById(R.id.editText)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        textCountryNameData = findViewById(R.id.txtCountryNameData)
        textCountryCapitalData = findViewById(R.id.txtCountryCapitalData)
        textCountryPopulationData = findViewById(R.id.txtCountryPopulationData)
        textCountryCallCodeData = findViewById(R.id.txtCountryCallCodeData)
    }
    var job: Job? = null
    private fun initListeners() {
        buttonSubmit!!.setOnClickListener {
            // TODO
            // search by the given country name, and
            // 1. pin point to the map
            // 2. set the country information to the textViews.
            val searchQuery = editText?.text
            Log.d("search query", searchQuery.toString())
            googleMap!!.clear()
            lifecycleScope.launch{
                mapsViewModel.getFromSearchBar(searchQuery.toString())
            }
        }
    }

    private fun loadMap() {
        mapFragment!!.getMapAsync { googleMap ->
            this@MapsActivity.googleMap = googleMap
        }
    }


    private fun loadingMarker(latitude: Double, longitude:Double){
        val coordinate: LatLng = LatLng(latitude, longitude)

        googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 4F))
        addMapsMarker(coordinate)

    }

    private fun addMapsMarker(data:LatLng) {
        googleMap!!.addMarker(MarkerOptions().position(data).title("Marker"))
    }


    private fun observeData(){
        mapsViewModel.responseMapsData.observe(this, Observer { data ->
            textCountryNameData.text = data.results[0].formatted_address
            val lintang = data.results[0].geometry.location.lat
            val bujur = data.results[0].geometry.location.lng
            lifecycleScope.launch { loadingMarker(lintang, bujur) }
        })
        mapsViewModel.responseCountryData.observe(this, Observer{ response ->
        when (response){
            is Resource.Loading -> {
                textCountryCapitalData.text = getString(R.string.loading)
                textCountryPopulationData.text = getString(R.string.loading)
                textCountryCallCodeData.text = getString(R.string.loading)
            }
            is Resource.Success -> {
            textCountryCapitalData.text = response.data?.get(0)?.capital
            textCountryPopulationData.text = response.data?.get(0)?.population.toString()
            textCountryCallCodeData.text = response.data?.get(0)?.callingCodes?.map{it}.toString()
            }
            is Resource.Error -> {
                textCountryCapitalData.text = getString(R.string.not_found)
                textCountryPopulationData.text = getString(R.string.not_found)
                textCountryCallCodeData.text = getString(R.string.not_found)
            }

        }

        })
    }




}
