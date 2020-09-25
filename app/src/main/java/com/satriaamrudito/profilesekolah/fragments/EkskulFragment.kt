package com.satriaamrudito.profilesekolah.fragments

import android.os.Bundle
import android.util.Log
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
import com.satriaamrudito.profilesekolah.room.RoomDB
import kotlinx.android.synthetic.main.fragment_ekskul.view.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class EkskulFragment : Fragment() {

    private lateinit var adapterRv: GaleriItemListAdapter
    private lateinit var loadingDialog: LoadingDialog

    private lateinit var roomDB: RoomDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ekskul, container, false)
        loadingDialog = LoadingDialog(view.context, container!!)

        roomDB = RoomDB.getInstance(view.context)

        adapterRv = GaleriItemListAdapter()
        view.rv_ekskul.setHasFixedSize(true)
        view.rv_ekskul.layoutManager = LinearLayoutManager(view.context)
        view.rv_ekskul.adapter = adapterRv
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog.startLoadingDialog()



        val dataItem = roomDB.roomDao().getDataByType("ekskul")
        dataItem.observe(viewLifecycleOwner, {
            Log.e("banyak data", it.size.toString())
            if (it.isNotEmpty()) {
                adapterRv.addData(it)
                adapterRv.notifyDataSetChanged()
                loadingDialog.dismissDialog()
            } else {
                Toast.makeText(context, "Database Kosong", Toast.LENGTH_SHORT).show()

                val retrofitService = RetrofitService.buildService(RetrofitInterfaces::class.java)

                val handlerThread = CoroutineExceptionHandler { _, exception ->
                    Toast.makeText(
                        context,
                        "Tidak bisa menghubungi Server..\nSilahkan Periksa koneksi Internet",
                        Toast.LENGTH_LONG
                    ).show()
                }

                viewLifecycleOwner.lifecycleScope.launch(handlerThread) {

                    val request = retrofitService.getDataEkskul()
                    if (request.isSuccessful) { // jika request sukses
                        val dataEkskul = request.body() as List<ItemRv>
                        dataEkskul.forEach{
                            it.type = "ekskul"
                        }
                        roomDB.roomDao().insertData(dataEkskul)
                    }
                }
            }
        })
    }
}
