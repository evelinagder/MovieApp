package com.example.newsfeed

import android.os.Bundle
import android.view.View
import com.example.moviesapp.view.BaseFragment

class NewsFeedFragment : BaseFragment<com.example.newsfeed.databinding.FragmentNewsfeedBinding, NewsFeedFragmentViewModel>() {

    override fun getViewModelResId(): Int = BR.newsfeedFragmentVM

    override fun getLayoutResId(): Int = R.layout.fragment_newsfeed

    override fun getViewModelClass(): Class<NewsFeedFragmentViewModel> = NewsFeedFragmentViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  viewModel.getListTrigger.value = true
    }
}