package com.example.moviesapp.app.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.utils.Event

class LoginViewModel: ViewModel() {

	val username = object : ObservableField<String>() {
		override fun set(value: String?) {
			super.set(value)
			if (value?.isEmpty() == false) {
				isErrorVisible.set(false)
			}
		}
	}

	val password = object : ObservableField<String>() {
		override fun set(value: String?) {
			super.set(value)
			if (value?.isEmpty() == false) {
				isErrorVisible.set(false)
			}
		}
	}

	val isErrorVisible = ObservableBoolean()

	val isLoginEnabled = object : ObservableBoolean(username, password) {
		override fun get(): Boolean {
			return isInputValid(username.get()) && isInputValid(password.get())
		}
	}

	private val _isLoginClicked = MutableLiveData<Event<Boolean>>()
	val isLoginClicked: LiveData<Event<Boolean>>
		get() = _isLoginClicked

	private fun isInputValid(input: String?) = !input.isNullOrEmpty() && input.length >= 3

	fun login(){
		_isLoginClicked.value = Event(true)
	}

	fun clearInputFields() {
		username.set("")
		password.set("")
	}
}