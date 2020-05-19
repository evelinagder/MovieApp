package com.example.moviesapp.app.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.example.moviesapp.R
import com.example.moviesapp.app.MainActivityViewModel
import com.example.moviesapp.app.MainActivityViewModel.Companion.NAVIGATION_STEP_HOME
import com.example.moviesapp.utils.setVisible
import kotlinx.android.synthetic.main.fragment_login.*

const val USERNAME_PREFS_KEY = "usernameKey"
const val IS_USER_LOGGED = "isUserLogged"

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //show dynamic set actionbar title
        (activity as AppCompatActivity).supportActionBar?.title = null

        //shared view model with activity scope
        val activityViewModel: MainActivityViewModel by activityViewModels()
        // view model only for the fragment
        val viewModel: LoginViewModel by viewModels()
        wrong_credentials_tv.setVisible(false)
        viewModel.isLoginClicked.observe(viewLifecycleOwner, Observer {
            if (it.getContentIfNotHandled() == true) {
                if (canLogin(username_et.text.toString())) {
                    val imm =
                        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    //We use Global Action to navigate from the nested graph
                    val navDirections = LoginFragmentDirections.actionGlobalHomeFragment()
                    Navigation.findNavController(view).navigate(navDirections)
                    activityViewModel.homeNavigationLiveData.value = NAVIGATION_STEP_HOME
                } else {
                    wrong_credentials_tv.setVisible(true)
                    clearInputFields()
                }
                return@Observer
            }
        })

        login_btn.setOnClickListener {
            if (viewModel.isLoginEnabled(username_et.text.toString())
            ) {
                viewModel.login()
            } else {
                Toast.makeText(
                    view.context,
                    "Username and Password are not valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        register_btn.setOnClickListener {
            val navDirections = LoginFragmentDirections.goToRegistration()
            Navigation.findNavController(view).navigate(navDirections)
        }
    }

    private fun canLogin(username: String?): Boolean {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
        val savedUsername = sharedPreferences.getString(USERNAME_PREFS_KEY, "") ?: ""
        if (username == savedUsername) {
            sharedPreferences.edit().putBoolean(IS_USER_LOGGED, true).apply()
            return true
        }
        return false
    }

    private fun clearInputFields() {
        username_et.setText("")
    }
}