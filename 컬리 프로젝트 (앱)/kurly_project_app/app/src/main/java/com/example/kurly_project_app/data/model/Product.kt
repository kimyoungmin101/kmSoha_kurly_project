package com.example.kurly_project_app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val title: String?,
    val price: String?,
    val url : String?
) : Parcelable