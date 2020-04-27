package com.example.movieslibrary

import android.os.Bundle
import android.view.View
import com.example.moviesapp.view.BaseFragment

class LibraryFragmentHome : BaseFragment<com.example.movieslibrary.databinding.FragmentLibraryHomeBinding, LibraryFragmentViewModel>() {


    override fun getViewModelResId(): Int = BR.libraryFragmentVM

    override fun getLayoutResId(): Int = R.layout.fragment_library_home

    override fun getViewModelClass(): Class<LibraryFragmentViewModel> = LibraryFragmentViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  viewModel.getListTrigger.value = true
    }
}