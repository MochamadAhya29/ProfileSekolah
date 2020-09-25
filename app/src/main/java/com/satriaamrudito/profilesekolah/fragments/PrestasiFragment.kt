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
import com.satriaamrudito.profilesekolah.model.Prestasi
import com.satriaamrudito.profilesekolah.recyclerview.PrestasiItemListAdapter
import com.satriaamrudito.profilesekolah.retrofit.RetrofitInterfaces
import com.satriaamrudito.profilesekolah.retrofit.RetrofitService
import kotlinx.android.synthetic.main.prestasi_item_list.view.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PrestasiFragment : Fragment() {

    private lateinit var adapterRv: PrestasiItemListAdapter
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prestasi, container, false)
        loadingDialog = LoadingDialog(view.context,container!!)
        adapterRv = PrestasiItemListAdapter()
        view.rv_prestasi.setHasFixedSize(true)
        view.rv_prestasi.layoutManager = LinearLayoutManager(view.context)
        view.rv_prestasi.adapter = adapterRv
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

            val request = retrofitService.getDataPrestasi()
            if (request.isSuccessful) { // jika request sukses
                val dataPrestasi = request.body() as List<Prestasi>
                adapterRv.addData(dataPrestasi)
                adapterRv.notifyDataSetChanged()
                loadingDialog.dismissDialog()
            }
        }
    }
}