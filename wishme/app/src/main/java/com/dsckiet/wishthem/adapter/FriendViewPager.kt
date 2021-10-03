package com.dsckiet.wishthem.adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class FriendViewPager (val images : List<Int>) : RecyclerView.Adapter<FriendViewPager.ViewPagerViewHolder>()
{
    var color_icon_matrix = arrayOf(
        intArrayOf(android.R.color.holo_red_dark, R.drawable.ic_input_add),
        intArrayOf(android.R.color.holo_red_dark, R.drawable.ic_input_add),
        intArrayOf(android.R.color.holo_red_dark, R.drawable.ic_input_add),
        intArrayOf(android.R.color.holo_red_dark, R.drawable.ic_input_add),

        )

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.dsckiet.wishthem.R.layout.friend_viewpager_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage = images[position]
        holder.itemView.findViewById<ImageView>(com.dsckiet.wishthem.R.id.ivImage).setImageResource(curImage)

        if(position == 1){

        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

}