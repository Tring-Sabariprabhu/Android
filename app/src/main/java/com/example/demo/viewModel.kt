package com.example.demo

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MainViewModel: ViewModel() {
    val count = MutableLiveData<Int>(1)

    fun updateCount(value: Int){
        count.postValue(value)
    }
    fun increaseCount(){
        count.postValue(count.value?.plus(1))
    }
    fun decreaseCount(){
        count.postValue(count.value?.minus(1))
    }
}