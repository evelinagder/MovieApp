package com.example.moviesapp.app


import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.view.BaseFragment

class HomeFragment : BaseFragment<com.example.moviesapp.databinding.FragmentHomeBinding, HomeFragmentViewModel>() {

    override fun getViewModelResId(): Int = BR.homeFragmentVM

    override fun getLayoutResId(): Int =R.layout.fragment_home

    override fun getViewModelClass(): Class<HomeFragmentViewModel> = HomeFragmentViewModel::class.java

}