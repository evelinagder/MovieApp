package com.example.moviesapp.app.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.utils.Event

class LoginViewModel : ViewModel() {

    val isErrorVisible = ObservableBoolean()

    fun isLoginEnabled(username:String, password:String) =
    isInputValid(username) && isInputValid(password)


    private val _isLoginClicked = MutableLiveData<Event<Boolean>>()
    val isLoginClicked: LiveData<Event<Boolean>>
        get() = _isLoginClicked

    private fun isInputValid(input: String?) = !input.isNullOrEmpty() && input.length >= 3

    fun login() {
        _isLoginClicked.value = Event(true)
    }

}