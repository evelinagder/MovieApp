package com.example.moviesapp.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var helloText = MutableLiveData<String>().apply { setValue("HELLOO") }
}
