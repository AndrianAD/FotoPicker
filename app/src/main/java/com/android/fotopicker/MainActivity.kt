package com.android.fotopicker

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.android.fotopicker.Adapter.SimpleAdaptor
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


const val PICK_IMAGE = 1
const val TYPE = "TYPE"

class MainActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE = 2
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this)[BaseViewModel::class.java]
        requestPermissions()
        recyclerView.layoutManager = GridLayoutManager(this, 3)



        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(TYPE, type)
            startActivityForResult(intent, PICK_IMAGE)
        }


        var adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this,
            R.array.harmonica_key,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        tonica.adapter = adapter
        tonica.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    itemSelected: View, selectedItemPosition: Int, selectedId: Long
                ) {
                    val choose = resources.getStringArray(R.array.harmonica_key)
                    type = choose[selectedItemPosition]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val ar1: ArrayList<File>? = data.extras!!.getParcelableArrayList("test")
                val productAdapter = ar1?.let { SimpleAdaptor(it) }
                recyclerView.adapter = productAdapter
            }
        }
    }


    fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("OK") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE
                    )
                }
                .setNegativeButton("Cancel")
                { dialog, _ -> dialog.dismiss() }
                .create().show()

        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivityForResult(intent, requestCode)
            }
        }
    }
}
