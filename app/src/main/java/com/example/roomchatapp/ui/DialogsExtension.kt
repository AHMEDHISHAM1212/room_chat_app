package com.example.roomchatapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showDialog(
    message: String,
    posActionName: String? = null,
    posAction: DialogInterface.OnClickListener? = null,
    negActionName: String? = null,
    negAction: DialogInterface.OnClickListener? = null,

    ) {
    val dialog = AlertDialog.Builder(context)
    dialog.setMessage(message)

    if (posActionName != null) {
        dialog.setPositiveButton(posActionName, posAction)
    }
    if (negActionName != null) {
        dialog.setNegativeButton(negActionName, negAction)
    }


}

fun Activity.showDialog(
    message: String,
    posActionName: String? = null,
    posAction: DialogInterface.OnClickListener? = null,
    negActionName: String? = null,
    negAction: DialogInterface.OnClickListener? = null,

    ) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)


    if (posActionName != null) {
        builder.setPositiveButton(posActionName, posAction)
    }
    if (negActionName != null) {
        builder.setNegativeButton(negActionName, negAction)
    }

    val alertDialog = builder.create()
    alertDialog.show()


}