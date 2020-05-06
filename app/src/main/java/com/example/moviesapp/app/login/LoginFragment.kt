package com.example.moviesapp.app.login

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.app.registration.RegistrationViewModel
import com.example.moviesapp.databinding.FragmentLoginBinding
import com.example.moviesapp.view.BaseFragment

const val USERNAME_PREFS_KEY = "usernameKey"
const val PASSWORD_PREFS_KEY = "passwordKey"
const val IS_USER_LOGGED = "isUserLogged"

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

	override fun getViewModelResId() = BR.viewModel

	override fun getLayoutResId() = R.layout.fragment_login

	override fun getViewModelClass() = LoginViewModel::class.java

	override fun getActionBarTitle(): String? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val vm: RegistrationViewModel by activityViewModels()

		viewModel.isLoginClicked.observe(viewLifecycleOwner, Observer {
			if (it.getContentIfNotHandled() == true) {
				if (canLogin(viewModel.username.get(), viewModel.password.get())) {
					//We use Global Action to navigate from the nested graph
					val navDirections = LoginFragmentDirections.actionGlobalHomeFragment()
					Navigation.findNavController(view).navigate(navDirections)
					vm.navigationStageLiveData.value = RegistrationViewModel.NAVIGATION_STEP_HOME
				} else {
					viewModel.isErrorVisible.set(true)
					viewModel.clearInputFields()
				}
				return@Observer
			}
		})

		binding.registerBtn.setOnClickListener {
			val navDirections = LoginFragmentDirections.goToRegistration()
			Navigation.findNavController(view).navigate(navDirections)
		}
	}

	private fun canLogin(username: String?, password: String?): Boolean {
		val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		val savedUsername = sharedPreferences.getString(USERNAME_PREFS_KEY, "") ?: ""
		val savedPassword = sharedPreferences.getString(PASSWORD_PREFS_KEY, "") ?: ""
		if (username == savedUsername && password == savedPassword){
			sharedPreferences.edit().putBoolean(IS_USER_LOGGED, true).apply()
			return true
		}
		return false
	}
}