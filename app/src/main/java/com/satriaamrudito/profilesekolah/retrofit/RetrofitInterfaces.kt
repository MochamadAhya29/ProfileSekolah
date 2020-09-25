package com.satriaamrudito.profilesekolah.retrofit

import com.satriaamrudito.profilesekolah.model.Founder
import com.satriaamrudito.profilesekolah.model.ItemRv
import com.satriaamrudito.profilesekolah.model.Prestasi
import com.satriaamrudito.profilesekolah.model.Sekolah
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterfaces {

    // suspend fun digunakan untuk membuat fungsi yang berjalan Asynchronous
    @GET("data/ekskul.json")
    suspend fun getDataEkskul(): Response<List<ItemRv>>

    // nanti hasil akhirnya adalah baseUrl + Get
    // https://profil-sekolah-5bf9b.firebaseio.com/data/galeri.json
    @GET("data/galeri.json")
    suspend fun getDataGaleri(): Response<List<ItemRv>>

    @GET("data/prestasi.json")
    suspend fun getDataPrestasi(): Response<List<Prestasi>>

    @GET("data/kepsek.json")
    suspend fun getDataFounder(): Response<Founder>

    @GET("data/sekolah.json")
    suspend fun getDataSekolah(): Response<Sekolah>
}