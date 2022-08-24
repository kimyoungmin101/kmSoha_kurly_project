package com.example.booksearchapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kurly_project_app.data.model.Product
import retrofit2.Response
import java.sql.ResultSet

interface ProductsRepository {
    fun getProducts(): MutableList<Product>?
}