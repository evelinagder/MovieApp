package com.example.moviesapp.app


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.view.BaseFragment

class HomeFragment : BaseFragment<com.example.moviesapp.databinding.FragmentHomeBinding, HomeFragmentViewModel>() {

    override fun getViewModelResId(): Int = BR.homeFragmentVM

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun getViewModelClass(): Class<HomeFragmentViewModel> = HomeFragmentViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListTrigger.value = true
        Log.d("EVAA", "ТРИГГЕР}")
        viewModel.kidsMoviesResponse.observe(viewLifecycleOwner, Observer {
            Log.d("EVAA", "STATUS  ${it.status}")
        })
    }
}