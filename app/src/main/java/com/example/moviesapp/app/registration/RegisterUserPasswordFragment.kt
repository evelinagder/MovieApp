package com.example.moviesapp.app.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.moviesapp.R
import com.example.moviesapp.app.registration.RegistrationViewModel.Companion.NAVIGATION_STEP_USERNAME
import kotlinx.android.synthetic.main.fragment_username.*

class RegisterUserPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_username, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: RegistrationViewModel by navGraphViewModels(R.id.navigation_reg)

        viewModel.navigationStageLiveData.observe(
            viewLifecycleOwner,
            Observer { navigationStatus: String ->
                if (navigationStatus == NAVIGATION_STEP_USERNAME) {
                    Navigation.findNavController(view).navigate(R.id.go_to_age_fragment_action)
                }
            })

        button_registration_next_username.setOnClickListener {
            val username = username_edit_text.text.toString()
            val pass = password_edit_text.text.toString()
            if (username.isEmpty() || pass.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Username and Password cannot be empty!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            viewModel.addUserNamePassword(
                username,
                pass
            )
        }
    }
}
