package com.example.kurly_project_app.presentation.view.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ActionType{
    PLUS, MINUS
}

@HiltViewModel
class ProductViewModel @Inject constructor() : ViewModel() {

    private val _liveData = MutableLiveData<Int>()
    val liveData: LiveData<Int> get() = _liveData

    private val _liveDataCnt = MutableLiveData<Int>()
    val liveDataCnt: LiveData<Int> get() = _liveDataCnt

    init{
        _liveData.value = 0
        _liveDataCnt.value = 0
    }

    fun updateValue(actionType: ActionType, input:Int){
        if (actionType == ActionType.PLUS){
            _liveData.value = _liveData.value?.plus(input)
            _liveDataCnt.value = _liveDataCnt.value?.plus(1)
        }
        else{
            if(_liveData.value != 0){
                _liveData.value = _liveData.value?.minus(input)
                _liveDataCnt.value = _liveDataCnt.value?.minus(1)
            }
        }
    }

    fun clearValue(){
        _liveData.value = 0
        _liveDataCnt.value = 0
    }
}
