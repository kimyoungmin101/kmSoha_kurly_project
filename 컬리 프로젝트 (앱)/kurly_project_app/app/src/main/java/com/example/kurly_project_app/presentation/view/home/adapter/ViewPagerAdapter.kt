package com.example.kurly_project_app.presentation.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kurly_project_app.R

class ViewPagerAdapter(var aespaMembers: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_list_item, parent, false)) {
        val itemMember = itemView.findViewById<ImageView>(R.id.imageView_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = aespaMembers.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.itemMember.setImageResource(aespaMembers[position])
    }
    }

