package com.satriaamrudito.profilesekolah.model

import com.squareup.moshi.Json

data class Founder(

	@Json(name="image")
	val image: String? = null,

	@Json(name="twitter")
	val twitter: String? = null,

	@Json(name="background")
	val background: String? = null,

	@Json(name="facebook")
	val facebook: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="instagram")
	val instagram: String? = null,

	@Json(name="email")
	val email: String? = null
)
