package com.satriaamrudito.profilesekolah

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

class LoadingDialog(context: Context, viewGroup: ViewGroup) {

    private val inflater = LayoutInflater.from(context)
    private val viewLoadingBar =
        inflater.inflate(R.layout.activity_loading_dialog, viewGroup, false)
    private val mContext = context

    private lateinit var dialog: AlertDialog

    fun startLoadingDialog() {
        if (viewLoadingBar.parent == null) {
            val alertDialog = AlertDialog.Builder(mContext)
            alertDialog.setView(viewLoadingBar)
            alertDialog.setCancelable(false)
            dialog = alertDialog.create()
        }
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }
}