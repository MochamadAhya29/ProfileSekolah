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
import com.satriaamrudito.profilesekolah.model.Sekolah
import com.satriaamrudito.profilesekolah.retrofit.RetrofitInterfaces
import com.satriaamrudito.profilesekolah.retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_sekolah.*
import kotlinx.android.synthetic.main.sekolah_content.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SekolahFragment : Fragment() {

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sekolah, container,  false)
        loadingDialog= LoadingDialog(view.context,container!!)
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
            val request = retrofitService.getDataSekolah()
            if (request.isSuccessful) { // jika request sukses
                val dataSekolah = request.body() as Sekolah
                Glide.with(this@SekolahFragment)
                    .load(dataSekolah.image)
                    .into(img_school)
                sekolah_title.text = dataSekolah.title
                txt_npsn.text = dataSekolah.npsn
                txt_alamat.text = dataSekolah.address
                txt_telepon.text = dataSekolah.phone
                txt_website.text = dataSekolah.website

                txt_instagram.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataSekolah.instagram))
                    startActivity(intent)
                }

                txt_facebook.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataSekolah.facebook))
                    startActivity(intent)
                }

                txt_twitter.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataSekolah.twitter))
                    startActivity(intent)
                }
                loadingDialog.dismissDialog()
            }
        }
    }
}
