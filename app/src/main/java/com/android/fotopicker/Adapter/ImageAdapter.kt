package com.example.magentotest.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.fotopicker.App
import com.android.fotopicker.File
import com.android.fotopicker.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_view_element.view.*


class ImageAdapter(imageList: List<File>) : RecyclerView.Adapter<ImageAdapter.UserViewHolder>() {
    var imageList: List<File> = imageList
    lateinit var context: Context
    var index = 1
    var positionList = mutableListOf<UserViewHolder>()

    fun setItemList(items: List<File>) {
        this.imageList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.recycler_view_element, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(parent: UserViewHolder, position: Int) {


        Glide.with(context)
            .load(imageList[position].path)
            .centerCrop()
            .error(R.drawable.ic_launcher_background)
            .into(parent.imageView)

        parent.name.text = imageList[position].name


        parent.itemView.setOnClickListener {
            val color: Int
            if (parent.cardView.cardBackgroundColor != ColorStateList.valueOf(Color.BLUE)) {
                color = Color.BLUE
                App.selectedImage.add(imageList[position])
                parent.number.visibility = View.VISIBLE
                parent.number.text = " ${index - 1} "
                index++
                positionList.add(parent)
            } else {
                color = Color.WHITE
                parent.number.visibility = View.INVISIBLE
                index--
                positionList.remove(parent)
                App.selectedImage.remove(imageList[position])
                var parentText = parent.number.text.toString().drop(1).dropLast(1).toInt()
                for (index in parentText until positionList.size) {
                    positionList[index].number.text =
                        " ${(positionList[index].number.text.toString().drop(1).dropLast(1).toInt() - 1)} "
                }
            }
            parent.cardView.setCardBackgroundColor(color)

        }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.imageView1
        var cardView: CardView = view.cardView
        var number: TextView = view.number
        var name: TextView = view.name
    }
}


