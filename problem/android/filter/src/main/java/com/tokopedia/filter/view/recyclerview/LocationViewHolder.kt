package com.tokopedia.filter.view.recyclerview

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.tokopedia.filter.R

class LocationViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val chipLocation: Chip = view.findViewById(R.id.chip)


}