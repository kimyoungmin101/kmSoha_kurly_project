package com.example.kurly_project_app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "product_order")
data class ProductOrder(
    val order_num: Int?,
    val product_id: Int?,
    val user_id: Int,
    val product_count: Int?,
    val order_success: Int?,
    val product_price: Int?,
    val delivery_date: Date,
) : Parcelable

//order_num: Int, product_id: Int, user_id: Int, product_count: Int, order_success : Int, product_price : Int, delivery_date : Int
