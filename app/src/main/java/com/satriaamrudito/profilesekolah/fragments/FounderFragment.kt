package com.satriaamrudito.profilesekolah.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.satriaamrudito.profilesekolah.LoadingDialog
import com.satriaamrudito.profilesekolah.R
import com.satriaamrudito.profilesekolah.model.Founder
import com.satriaamrudito.profilesekolah.retrofit.RetrofitInterfaces
import com.satriaamrudito.profilesekolah.retrofit.RetrofitService
import kotlinx.android.synthetic.main.founder_content.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class FounderFragment : Fragment() {

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_founder, container, false)
        loadingDialog = LoadingDialog(view.context, container!!)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog.startLoadingDialog()

        val handlerThread = CoroutineExceptionHandler { _, error ->
            Toast.makeText(
                context,
                "Tidak bisa menghubungi Server..\nSilahkan Periksa koneksi Internet",
                Toast.LENGTH_LONG
            ).show()
        }
        viewLifecycleOwner.lifecycleScope.launch(handlerThread) {
            val retrofitService = RetrofitService.buildService(RetrofitInterfaces::class.java)
            // jalankan fungsi getDataGaleri yang berjalan secara asynchronous / di background
            val request = retrofitService.getDataFounder()
            if (request.isSuccessful) { // jika request sukses
                val dataFounder = request.body() as Founder
                Glide.with(this@FounderFragment)
                    .load(dataFounder.image)
                    .circleCrop()
                    .into(img_view)
                txt_judul.text = dataFounder.name
                txt_email.text = dataFounder.email
                txt_latarbelakang.text = dataFounder.background

                btn_facebook.setOnClickListener {
                    // Uri.parse untuk mengubah URL String ke bentuk Uri ( Uniform Resource Identifier (URI) )
                    val faceb = Intent(Intent.ACTION_VIEW, Uri.parse(dataFounder.facebook))
                    startActivity(faceb)
                }

                btn_instagram.setOnClickListener {
                    val insta = Intent(Intent.ACTION_VIEW, Uri.parse(dataFounder.instagram))
                    startActivity(insta)
                }

                btn_twitter.setOnClickListener {
                    val twit = Intent(Intent.ACTION_VIEW, Uri.parse(dataFounder.twitter))
                    startActivity(twit)
                }
                loadingDialog.dismissDialog()
            }
        }
    }
}