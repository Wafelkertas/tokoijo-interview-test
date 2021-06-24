package com.tokopedia.filter.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.filter.R
import com.tokopedia.filter.view.recyclerview.ProductAdapter
import com.tokopedia.filter.view.viewmodel.ProductViewmodel

class ProductFragment : Fragment(R.layout.fragment_products_list) {
    lateinit var productViewmodel: ProductViewmodel
    private lateinit var productAdapter: ProductAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewmodel = (activity as ProductActivity).productViewmodel

        setupRecyclerView()
        observeData()
    }

    private fun observeData() {
        productViewmodel.observableData.observe(viewLifecycleOwner, Observer { data ->
            productAdapter.differ.submitList(data)
        })
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter()
        val recyclerView: RecyclerView = requireView().findViewById(R.id.product_list)
        recyclerView.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        }
    }
}