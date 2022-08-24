package com.example.kurly_project_app.presentation.view.home.viewmodel

import androidx.lifecycle.*
import com.example.booksearchapp.data.repository.ProductsRepository
import com.example.kurly_project_app.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) : ViewModel() {
    private val _items = MutableLiveData<MutableList<Product>>()
    val items: LiveData<MutableList<Product>>
        get() = _items

    fun getProducts() = viewModelScope.launch(Dispatchers.IO) {
            val productList: MutableList<Product>? = productsRepository.getProducts()
            _items!!.value = productList
        }
}