package com.tokopedia.filter.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.tokopedia.filter.R
import com.tokopedia.filter.view.recyclerview.LocationAdapter
import com.tokopedia.filter.view.recyclerview.ProductAdapter
import com.tokopedia.filter.view.viewmodel.ProductViewmodel
import java.text.NumberFormat
import java.util.*

class BottomSheetFilterFragment : BottomSheetDialogFragment() {

    lateinit var locationAdapter: LocationAdapter
    lateinit var productViewModel: ProductViewmodel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.bottom_sheet_filter, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = (activity as ProductActivity).productViewmodel
        setupRecyclerview()
        observeData()
        Log.d("Grouped list", productViewModel.listOfLocation.value.toString())


        val adapterData = locationAdapter.lokasiArray

        val filterBtn: Button = requireView().findViewById(R.id.filter_button)
        Log.d("adapter data", adapterData.toString())
        val sliderView: RangeSlider = requireView().findViewById(R.id.slider_value)

        formatSlider(sliderView)


        filterBtn.setOnClickListener {
            filterSubmit(adapterData, sliderView)
        }


    }

    private fun formatSlider(slider: RangeSlider) {
        slider.setLabelFormatter { value: Float ->
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("IDR")
            format.format(value.toInt() * 100000)
        }
    }


    private fun filterSubmit(data: List<String>, slider: RangeSlider) {


        val lowestPriceEditText: EditText = requireView().findViewById(R.id.lowest_price_EditText)
        val highestPriceEditText: EditText = requireView().findViewById(R.id.highest_price_EditText)
        val lowestPriceData = lowestPriceEditText.text
        val highestPriceData = highestPriceEditText.text
        Log.d("Value from slider", slider.values.toString())

        if (lowestPriceData.isNotBlank() && highestPriceData.isNotBlank()) {
            productViewModel.sortingViewmodel(lokasi = data, hargaAwal = lowestPriceData.toString().toInt(), hargaAkhir = highestPriceData.toString().toInt())
        } else {
            val sliderViewFrom = slider.values[0]
            val sliderViewTo = slider.values[1]
            val sliderViewFromInt = sliderViewFrom.toInt() * 100000
            val sliderViewToInt = sliderViewTo.toInt() * 100000


            productViewModel.sortingViewmodel(lokasi = data, hargaAwal = sliderViewFromInt, hargaAkhir = sliderViewToInt)
        }


    }


    private fun observeData() {
        productViewModel.listOfLocation.observe(viewLifecycleOwner, Observer { data ->
            locationAdapter.differ.submitList(data)
        })
    }

    private fun setupRecyclerview() {
        locationAdapter = LocationAdapter()
        val recyclerView: RecyclerView = requireView().findViewById(R.id.location_list)
        recyclerView.apply {
            adapter = locationAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}