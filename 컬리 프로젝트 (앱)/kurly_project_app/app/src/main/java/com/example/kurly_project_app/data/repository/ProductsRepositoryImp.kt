package com.example.booksearchapp.data.repository

import android.util.Log
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.presentation.view.home.HomeFragment
import java.sql.DriverManager
import java.sql.ResultSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepositoryImp @Inject constructor(
) : ProductsRepository {
    override fun getProducts(): MutableList<Product>? {
        TODO("Not yet implemented")
    }

}