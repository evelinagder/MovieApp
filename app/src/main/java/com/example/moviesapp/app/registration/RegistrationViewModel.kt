package com.example.moviesapp.app.registration

import androidx.lifecycle.ViewModel
import com.example.service.model.User

class RegistrationViewModel : ViewModel() {

    private val user = User()

    fun addUserNamePassword(username: String, password: String) {
        user.userName = username
        user.password = password
        //TODO save registration state in shared preffs
    }

    fun addAge(age: Int) {
        user.age = age
    }
}