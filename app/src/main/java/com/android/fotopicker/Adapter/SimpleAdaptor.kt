package com.android.fotopicker.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.fotopicker.File
import com.android.fotopicker.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_view_element.view.*

class SimpleAdaptor(imageList: List<File>) :
    RecyclerView.Adapter<SimpleAdaptor.UserViewHolder>() {
    var imageList: List<File> = imageList
    lateinit var context: Context

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
            .circleCrop()
            .skipMemoryCache(true)
            .error(R.drawable.ic_launcher_background)
            .into(parent.imageView)


        parent.itemView.setOnClickListener {
        }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.imageView1
        var cardView: CardView = view.cardView
    }
}
