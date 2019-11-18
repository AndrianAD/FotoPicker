package com.android.fotopickermodule

import android.app.Activity
import android.widget.Toast


class FotoPicker {

    fun startPicker(activity: Activity, type: String) {
//    val intent = Intent(this, SecondActivity::class.java)
//    intent.putExtra(TYPE, type)
//    this.startActivityForResult(intent, PICK_IMAGE)
        Toast.makeText(activity, "Hello From Photo Picker", Toast.LENGTH_LONG)


    }
}
