package com.example.moviesapp.app.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.service.model.User

open class RegistrationViewModel : ViewModel() {

    private val user = User()

    //we use live data to trigger navigation changes in the fragments
    val navigationStageLiveData = MutableLiveData<String>()

	fun getUsername() = user.userName
	fun getUserPass() = user.password

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
        navigationStageLiveData.value = NAVIGATION_STEP_DONE

        //SHOW that we have all the saved info
        Log.d("Movies", "Name " + user.userName)
    }

    companion object {
        const val NAVIGATION_STEP_USERNAME = "username"
        const val NAVIGATION_STEP_AGE = "age"
        const val NAVIGATION_STEP_DONE = "done"
        const val NAVIGATION_STEP_HOME = "home"
    }
}