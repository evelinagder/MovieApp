package com.example.moviesapp.app


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.app.recyclerview.MoviesAdapter
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.view.BaseFragment
import com.example.service.model.Status

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {


    override fun getViewModelResId(): Int = BR.homeFragmentVM

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun getViewModelClass(): Class<HomeFragmentViewModel> = HomeFragmentViewModel::class.java

	override fun getActionBarTitle() = getString(R.string.home_screen_title)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesRecycler.layoutManager = LinearLayoutManager(this@HomeFragment.context)
        viewModel.moviesResponse.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                val moviesResult = it.data
                binding.moviesRecycler.adapter = MoviesAdapter(moviesResult?.results)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getListTrigger.value = true
    }
}