package com.tokopedia.filter.view

import android.app.ProgressDialog.show
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.filter.R
import com.tokopedia.filter.view.recyclerview.ProductAdapter
import com.tokopedia.filter.view.repository.ProductRepository
import com.tokopedia.filter.view.viewmodel.ProductViewModelProviderFactory
import com.tokopedia.filter.view.viewmodel.ProductViewmodel
import kotlinx.coroutines.launch


class ProductActivity : AppCompatActivity() {
    lateinit var productViewmodel: ProductViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        val productRepository = ProductRepository()
        val mapsViewModelProviderFactory = ProductViewModelProviderFactory(productRepository)
        productViewmodel = ViewModelProvider(this, mapsViewModelProviderFactory).get(ProductViewmodel::class.java)


        lifecycleScope.launch {
            productViewmodel.getProductData()

        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val filterButton: ImageButton = findViewById(R.id.filterButton)
        filterButton.setOnClickListener {
            navHostFragment.findNavController().navigate(R.id.action_productFragment_to_bottomSheetFilterFragment)
        }
    }


}