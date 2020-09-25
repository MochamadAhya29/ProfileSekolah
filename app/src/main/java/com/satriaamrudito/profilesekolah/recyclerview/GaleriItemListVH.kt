package com.satriaamrudito.profilesekolah.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.satriaamrudito.profilesekolah.model.ItemRv
import kotlinx.android.synthetic.main.galeri_item_list.view.*

class GaleriItemListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ItemRv) {
        itemView.run {
            txt_title.text = item.title
            txt_description.text = item.description
            Glide.with(this).load(item.urlImage).into(img_item)
        }
    }
}