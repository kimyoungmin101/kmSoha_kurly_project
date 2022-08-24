package com.example.booksearchapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kurly_project_app.data.model.ListItem
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.databinding.DetailProductListItemBinding
import com.example.kurly_project_app.databinding.ProductItemListBinding
import com.example.kurly_project_app.presentation.view.calendar.adapter.ListViewHolder

class ListViewAdapter : ListAdapter<ListItem, ListViewHolder>(BookDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            DetailProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((ListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ListItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val product = currentList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(product)
            }
        }
    }

    companion object {
        private val BookDiffCallback = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.product_id == newItem.product_id
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}