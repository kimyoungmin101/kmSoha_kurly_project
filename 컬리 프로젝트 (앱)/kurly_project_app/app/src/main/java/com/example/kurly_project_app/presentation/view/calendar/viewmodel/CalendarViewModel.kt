package com.example.kurly_project_app.presentation.view.calendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchapp.data.repository.ProductsRepository
import com.example.kurly_project_app.data.model.Product
import com.example.kurly_project_app.presentation.view.product.viewmodel.ActionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {

    private val _liveData = MutableLiveData<Int>()
    val liveData: LiveData<Int> get() = _liveData


}