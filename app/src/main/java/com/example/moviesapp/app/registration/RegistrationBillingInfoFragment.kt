package com.example.moviesapp.app.registration

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.moviesapp.R
import com.example.moviesapp.app.login.USERNAME_PREFS_KEY
import kotlinx.android.synthetic.main.fragment_billing_info.*

class RegistrationBillingInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_billing_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: RegistrationViewModel by navGraphViewModels(R.id.navigation_reg)

        viewModel.navigationStageLiveData.observe(
            viewLifecycleOwner,
            Observer { navigationStatus: String ->
                if (navigationStatus == RegistrationViewModel.NAVIGATION_STEP_DONE) {
					val navDirections = RegistrationBillingInfoFragmentDirections.toLoginAction()
					Navigation.findNavController(view).navigate(navDirections)
                }
            })

        button_registration_done.setOnClickListener {
            val cardNumberValue: String = card_number_edit_text.text.toString()
            val cardName = card_name_text_view.text.toString()
            if (cardNumberValue.isEmpty() || cardName.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Card number and Card name cannot be empty!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val cardNumber = cardNumberValue.toInt()
            viewModel.addBillingInfo(
                cardNumber,
                cardName
            )
			saveUserCredentials(viewModel.getUsername())
        }
    }

	private fun saveUserCredentials(username:String?){
		val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
		sharedPreferences.edit().putString(USERNAME_PREFS_KEY, username).apply()
	}
}