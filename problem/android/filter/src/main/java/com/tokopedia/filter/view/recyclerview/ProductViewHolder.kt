package com.tokopedia.filter.view.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.filter.R

class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val productNameTv: TextView = view.findViewById(R.id.productNameTextTv)
    val productPriceTv: TextView = view.findViewById(R.id.productPriceTextTv)
    val productLocationTv: TextView = view.findViewById(R.id.productLocationTextTv)
}