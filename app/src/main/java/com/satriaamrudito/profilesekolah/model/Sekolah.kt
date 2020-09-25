package com.satriaamrudito.profilesekolah.model

import com.squareup.moshi.Json

data class Sekolah(

	@Json(name="image")
	val image: String? = null,

	@Json(name="twitter")
	val twitter: String? = null,

	@Json(name="website")
	val website: String? = null,

	@Json(name="address")
	val address: String? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="facebook")
	val facebook: String? = null,

	@Json(name="instagram")
	val instagram: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="npsn")
	val npsn: String? = null
)
