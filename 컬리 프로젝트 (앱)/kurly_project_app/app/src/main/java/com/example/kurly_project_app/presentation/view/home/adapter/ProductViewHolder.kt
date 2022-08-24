package com.example.booksearchapp.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.databinding.ProductItemListBinding

class ProductViewHolder(private val binding: ProductItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product : Product) {

        val product_title = product.title
        val product_price = product.price
        val product_url = product.url
        itemView.apply {
            binding.productName.text = product_title
            binding.productPrice.text = product_price
            binding.imgViewItem.load(product_url)
        }
    }
}