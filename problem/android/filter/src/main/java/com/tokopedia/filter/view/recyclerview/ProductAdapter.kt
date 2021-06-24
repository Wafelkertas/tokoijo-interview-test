package com.tokopedia.filter.view.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokopedia.filter.R
import com.tokopedia.filter.view.model.Product


class ProductAdapter: RecyclerView.Adapter<ProductViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.product_viewholder, viewGroup, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productData = differ.currentList[position]
        holder.itemView.let {
            Glide.with(it).load(productData.img).into(holder.itemView.findViewById(R.id.productImage))
            holder.productNameTv.text = productData.product
            val hargaDouble = productData.harga.toDouble()
            val formatted = String.format("%,.2f", hargaDouble)
            holder.productPriceTv.text = "Rp.${formatted}"
            holder.productLocationTv.text = productData.lokasi

        }
    }
}