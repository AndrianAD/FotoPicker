package com.android.fotopicker

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var imageList = MutableLiveData<ArrayList<File>>()


    fun getImages(type: String) {
        imageList.value = getImagesPath(getApplication(), type)
    }


    fun getImagesPath(context: Context, type: String): ArrayList<File> {
        val uri: Uri = android.provider.MediaStore.Files.getContentUri("external")
        val listOfAllImages = ArrayList<File>()
        val cursor: Cursor?
        val column_index_data: Int
        var pathOfImage: String? = null
        val projection =
            arrayOf(MediaStore.MediaColumns.DATA)

        var selection = "_data LIKE '%$type'"
        cursor = context.contentResolver.query(uri, projection, selection, null, null)

        column_index_data = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor.moveToNext()) {
            pathOfImage = cursor.getString(column_index_data)

            listOfAllImages.add(
                File(
                    name = pathOfImage.takeLastWhile { it != '/' },
                    path = pathOfImage
                )
            )
        }
        return listOfAllImages
    }
}