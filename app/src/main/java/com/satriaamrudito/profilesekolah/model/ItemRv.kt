package com.satriaamrudito.profilesekolah.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity (tableName = "item")
data class ItemRv(

    @PrimaryKey (autoGenerate = true)
    var uid : Int = 0,

    @Json(name = "urlImage")
    var urlImage: String = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "description")
    var description: String = "",
    var type: String = ""
)

data class Prestasi(
    @Json(name = "title")
    var title: String = "",
    @Json(name = "data")
    var data: List<ItemRv> = arrayListOf()
)