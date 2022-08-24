package com.example.kurly_project_app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val cart_number: Int?,
    val product_number: Int?,
    val cart_value: Int?, // 주문한 User ID
    val product_count : Int?,
) : Parcelable
