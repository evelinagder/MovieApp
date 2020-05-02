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
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.fragment_age.*

class RegistrationAgeCountryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: RegistrationViewModel by activityViewModels()

        viewModel.navigationStageLiveData.observe(
            viewLifecycleOwner,
            Observer { navigationStatus: String ->
                if (navigationStatus == RegistrationViewModel.NAVIGATION_STEP_AGE) {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_go_to_billingInfoFragment)
                }
            })

        button_registration_next_age.setOnClickListener {
            val ageValue: String = age_edit_text.text.toString()
            val country = country_edit_text.text.toString()
            if (ageValue.isEmpty() || country.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Age and Country cannot be empty!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val age = ageValue.toInt()
            viewModel.addAgeAndCountry(
                age,
                country
            )
        }
    }
}