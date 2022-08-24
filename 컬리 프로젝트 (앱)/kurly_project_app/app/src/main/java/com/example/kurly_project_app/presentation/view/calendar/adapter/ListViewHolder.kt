package com.example.kurly_project_app.presentation.view.calendar.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.booksearchapp.di.AppModule.getProduct
import com.example.kurly_project_app.data.model.ListItem
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.databinding.DetailProductListItemBinding
import com.example.kurly_project_app.databinding.ProductItemListBinding
import kotlinx.coroutines.*

class ListViewHolder(private val binding: DetailProductListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(listitem : ListItem) {

        var str_data = listitem.product_id

        //split 분리된 문자를 담을 배열 선언 실시
        var str_arr = str_data.split(" ")
        val result = str_arr[0]
        GlobalScope.launch {
            val product = getProduct(result.toInt())
            CoroutineScope(Dispatchers.Main).launch {
                val title_textview1 = product?.title
                val price_textView1 = product?.price
                val imageview1 = product?.url

                itemView.apply {
                    binding.titleTextview1.text = title_textview1
                    binding.priceTextView1.text = price_textView1
                    binding.imageview1.load(imageview1)
                }
            }
        }

        //배열에 담긴 데이터 출력 실시


    }
}