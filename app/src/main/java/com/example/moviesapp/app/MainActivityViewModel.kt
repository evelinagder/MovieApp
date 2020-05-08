package com.example.moviesapp.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val homeNavigationLiveData = MutableLiveData<String>()

    companion object {
        const val NAVIGATION_STEP_HOME = "home"
    }
}