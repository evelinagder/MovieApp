package com.example.moviesapp.app.registration

import android.os.Bundle
import android.view.View
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentAgeBinding
import com.example.moviesapp.view.BaseFragment

class RegistrationAgeCountryFragment : BaseFragment<FragmentAgeBinding, RegistrationViewModel>() {


    override fun getViewModelResId(): Int = BR.registrationFragmentVM

    override fun getLayoutResId(): Int = R.layout.fragment_age

    override fun getViewModelClass(): Class<RegistrationViewModel> =
        RegistrationViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}