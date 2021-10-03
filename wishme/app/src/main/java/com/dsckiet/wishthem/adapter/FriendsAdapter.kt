package com.dsckiet.wishthem.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.wishthem.Main2Fragment
import com.dsckiet.wishthem.R
import com.dsckiet.wishthem.entity.FriendsEntity

class FriendsAdapter(val context: Context, val listener: Main2Fragment): RecyclerView.Adapter<FriendsAdapter.NoteViewHolder>() {

    val allFriends = ArrayList<FriendsEntity>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nametextView = itemView.findViewById<TextView>(R.id.rec_item_name_txt)
        val taglinetextView = itemView.findViewById<TextView>(R.id.rec_item_tagline_txt)
        val dobtextView = itemView.findViewById<TextView>(R.id.rec_item_dob)
        val dp = itemView.findViewById<ImageView>(R.id.rec_item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_view_item,parent,false))
        viewHolder.dp.setOnClickListener {
            listener.onItemClicked(allFriends[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentfriend = allFriends[position]
        holder.nametextView.text = currentfriend.name
        holder.taglinetextView.text = currentfriend.tagline
        holder.dobtextView.text = currentfriend.dob
        holder.dp.setImageURI(currentfriend.image.toUri())

        holder.itemView.setOnClickListener {
            val name = currentfriend.name
            val tagline = currentfriend.tagline
            val flag = "true"
            val date = currentfriend.dob
            val image = currentfriend.image
            var itembundle = Bundle()
            itembundle.putString("Adnamebundle",name)
            itembundle.putString("Adtaglinebundle",tagline)
            itembundle.putString("Addobbundle",date)
            itembundle.putString("Adflag",flag)
            itembundle.putString("Adimage", image.toString())

            holder.itemView.findNavController().navigate(R.id.action_main2Fragment_to_onFriendClickedFragment,itembundle)
        }

    }



    override fun getItemCount(): Int {
        return allFriends.size
    }
    fun updateFriendsList(newlist:List<FriendsEntity>){
        allFriends.clear()
        allFriends.addAll(newlist)
        notifyDataSetChanged()
    }

}

interface INotesRvAdapter{
    fun onItemClicked(friendsEntity: FriendsEntity)
}