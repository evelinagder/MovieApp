package com.example.moviesapp.app.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.service.model.User

class RegistrationViewModel : ViewModel() {

    private val user = User()
    var isUserCompletedRegistration = false

    //TODO Observe the vents and trigger the navigation!
    val navigationStageLiveData = MutableLiveData<String>()

    fun addUserNamePassword(username: String, password: String) {
        user.userName = username
        user.password = password
        navigationStageLiveData.value = NAVIGATION_STEP_USERNAME
    }

    fun addAgeAndCountry(age: Int, country: String) {
        user.age = age
        user.country = country
        navigationStageLiveData.value = NAVIGATION_STEP_AGE
    }

    fun addBillingInfo(cardNumber: Int, cardHolderName: String) {
        user.cardNumber = cardNumber
        user.cardHolderName = cardHolderName
        isUserCompletedRegistration = true
        navigationStageLiveData.value = NAVIGATION_STEP_DONE
    }


    companion object {
        const val NAVIGATION_STEP_USERNAME = "username"
        const val NAVIGATION_STEP_AGE = "age"
        const val NAVIGATION_STEP_DONE = "done"
    }
}