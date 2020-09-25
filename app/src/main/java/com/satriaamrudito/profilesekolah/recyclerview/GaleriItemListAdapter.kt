package com.satriaamrudito.profilesekolah.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satriaamrudito.profilesekolah.R
import com.satriaamrudito.profilesekolah.model.ItemRv

class GaleriItemListAdapter : RecyclerView.Adapter<GaleriItemListVH>() {
    private var listItem = arrayListOf<ItemRv>()

    fun addData(items: List<ItemRv>) {
        listItem.clear()
        listItem.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriItemListVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.galeri_item_list, parent, false)
        return GaleriItemListVH(view)
    }

    override fun onBindViewHolder(holder: GaleriItemListVH, position: Int) {
        val data = listItem[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listItem.size
}