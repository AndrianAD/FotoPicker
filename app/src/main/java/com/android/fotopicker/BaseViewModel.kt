package com.android.fotopicker

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var imageList = MutableLiveData<ArrayList<String>>()


    fun getImages() {
        imageList.value = getImagesPath(getApplication())
    }


    fun getImagesPath(context: Context): ArrayList<String> {
        val uri: Uri
        val listOfAllImages = ArrayList<String>()
        val cursor: Cursor?
        val column_index_data: Int
        val column_index_folder_name: Int
        var PathOfImage: String? = null
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

        cursor = context.contentResolver.query(uri, projection, null, null, null)

        column_index_data = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        column_index_folder_name =
            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data)

            listOfAllImages.add(PathOfImage)
        }
        return listOfAllImages
    }
}