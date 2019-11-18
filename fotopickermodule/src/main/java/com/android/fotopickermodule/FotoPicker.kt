package com.android.fotopickermodule

import android.app.Activity
import android.widget.Toast

fun Activity.startPicker(type: String) {
//    val intent = Intent(this, SecondActivity::class.java)
//    intent.putExtra(TYPE, type)
//    this.startActivityForResult(intent, PICK_IMAGE)
    Toast.makeText(baseContext, "Hello From Photo Picker", Toast.LENGTH_LONG)
}