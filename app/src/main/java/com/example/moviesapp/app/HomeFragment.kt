package com.example.moviesapp.app


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.app.recyclerview.MoviesAdapter
import com.example.moviesapp.app.registration.RegistrationViewModel
import com.example.service.model.Status
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.home_screen_title)
        val viewModel: HomeFragmentViewModel by viewModels()

        // val viewModelTest: RegistrationViewModel by navGraphViewModels(R.id.navigation_reg)
        // this is going to crash -> No destination with ID 2131230944 is on the NavController's back stack


        movies_recycler.layoutManager = LinearLayoutManager(this@HomeFragment.context)
        viewModel.moviesResponse.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                val moviesResult = it.data
                movies_recycler.adapter = MoviesAdapter(moviesResult?.results)
            }
        })
        viewModel.getListTrigger.value = true
    }
}