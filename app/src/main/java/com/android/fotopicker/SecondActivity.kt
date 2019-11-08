package com.android.fotopicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.magentotest.Adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        var type = intent.getStringExtra(TYPE)
        val viewModel = ViewModelProviders.of(this)[BaseViewModel::class.java]
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        viewModel.getImages(type)

        viewModel.imageList.observe(this, Observer {
            val productAdapter = ImageAdapter(it)
            recyclerView.adapter = productAdapter
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.add_new_product -> {
                var intent = Intent(this, MainActivity::class.java)

                var arr = App.selectedImage as ArrayList<out Parcelable>
                intent.putParcelableArrayListExtra(
                    "test", arr
                )
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            R.id.table -> {
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
            R.id.grid -> {
                recyclerView.layoutManager = GridLayoutManager(this, 3)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
