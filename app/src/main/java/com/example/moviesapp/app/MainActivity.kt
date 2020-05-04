package com.example.moviesapp.app

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.app.registration.RegistrationViewModel
import com.example.moviesapp.view.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<com.example.moviesapp.databinding.ActivityMainBinding, MainActivityViewModel>() {

    override fun getViewModelResId(): Int = BR.mainActivityVM

    override fun getViewModelClass(): Class<MainActivityViewModel> =
        MainActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavigation()

        //listen for registration done and show bottom navigation
        val viewModel: RegistrationViewModel by viewModels()
        viewModel.navigationStageLiveData.observe(this, Observer {
            if (it == RegistrationViewModel.NAVIGATION_STEP_HOME) {
                bottom_nav.visibility = View.VISIBLE
                //This way we don`t go to registration start destination when we select Home tab
                (nav_host_fragment as NavHostFragment).changeStartDestination(R.id.homeFragment)
            }
        })

        //check if the user is registered and if not change the destination dynamically
        if (!viewModel.isUserCompletedRegistration) {
            (nav_host_fragment as NavHostFragment).changeStartDestination(R.id.navigation_reg)
            bottom_nav.visibility = View.GONE
        }
    }

    //TODO change live tv icon with appropriate one and
    // monitor and handle the installation progress states + customisation
    private fun setUpNavigation() {
        val bottomNavigationView = bottom_nav as BottomNavigationView
        bottomNavigationView.setupWithNavController(findNavController(R.id.nav_host_fragment))
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            findNavController(R.id.nav_host_fragment)
        )
    }

    /**
     * This ext functions changes the start Destination dynamically
     */
    private fun NavHostFragment.changeStartDestination(@IdRes startDestination: Int) {
        val graph = navController.graph
        graph.startDestination = startDestination
        navController.graph = graph
    }

}