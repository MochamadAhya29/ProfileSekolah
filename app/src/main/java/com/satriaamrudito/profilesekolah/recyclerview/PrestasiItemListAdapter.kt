package com.satriaamrudito.profilesekolah.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satriaamrudito.profilesekolah.R
import com.satriaamrudito.profilesekolah.model.Prestasi

class PrestasiItemListAdapter : RecyclerView.Adapter<PrestasiItemListVH>() {

    private var listItem = arrayListOf<Prestasi>()

    fun addData(items: List<Prestasi>) {
        listItem.clear()
        listItem.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrestasiItemListVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prestasi_item_list, parent, false)
        return PrestasiItemListVH(view)
    }

    override fun onBindViewHolder(holder: PrestasiItemListVH, position: Int) {
        val data = listItem[position]
        holder.bindData(data)
    }

    override fun getItemCount(): Int = listItem.size
}