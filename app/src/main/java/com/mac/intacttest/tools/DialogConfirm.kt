package com.mac.intacttest.tools

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class DialogConfirm(context: Context) {

    init {
        val builder = AlertDialog.Builder(context)
        //builder.setTitle("Androidly Alert")
        builder.setMessage("Are you sure you want to preceed to check out?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(
                context,
                android.R.string.yes, Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                context,
                android.R.string.no, Toast.LENGTH_SHORT
            ).show()
        }

    }

}