package com.example.aulaintroducaojetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var contador = 0

    //Observavel
    private val liveData = MutableLiveData<Int>()

    fun recuperarLiveData(): MutableLiveData<Int> {
        liveData.value = contador
        return liveData
    }

    fun incrementarContador() {
        contador++
        liveData.value = contador
    }
}