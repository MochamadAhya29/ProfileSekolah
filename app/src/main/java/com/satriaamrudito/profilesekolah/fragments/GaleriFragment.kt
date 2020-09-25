package com.satriaamrudito.profilesekolah.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriaamrudito.profilesekolah.LoadingDialog
import com.satriaamrudito.profilesekolah.R
import com.satriaamrudito.profilesekolah.model.ItemRv
import com.satriaamrudito.profilesekolah.recyclerview.GaleriItemListAdapter
import com.satriaamrudito.profilesekolah.retrofit.RetrofitInterfaces
import com.satriaamrudito.profilesekolah.retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_galeri.view.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class GaleriFragment : Fragment() {

    private lateinit var adapter: GaleriItemListAdapter
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_galeri, container, false)
        loadingDialog = LoadingDialog(view.context,container!!)

        adapter = GaleriItemListAdapter()
        view.rv_galeri.adapter = adapter
        view.rv_galeri.setHasFixedSize(true)
        view.rv_galeri.layoutManager = LinearLayoutManager(view.context)
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
            val request = retrofitService.getDataGaleri()
            if (request.isSuccessful) { // jika request sukses
                val dataGaleri = request.body() as List<ItemRv>
                adapter.addData(dataGaleri)
                adapter.notifyDataSetChanged()
                loadingDialog.dismissDialog()
            }
        }
    }
}