package com.example.moviesapp.app

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.view.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity :
	BaseActivity<com.example.moviesapp.databinding.ActivityMainBinding, MainActivityViewModel>() {

	override fun getViewModelResId(): Int = BR.mainActivityVM

	override fun getViewModelClass(): Class<MainActivityViewModel> =
		MainActivityViewModel::class.java

	override fun getLayoutResId(): Int = R.layout.activity_main

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setUpNavigation()
	}

	//TODO change live tv icon with appropriate one and
	// monitor and handle the installation progress states + customisation
	private fun setUpNavigation() {
		val bottomNavigationView = findViewById<View>(R.id.bottom_nav) as BottomNavigationView
		bottomNavigationView.setupWithNavController(findNavController(R.id.nav_host_fragment))
		NavigationUI.setupWithNavController(
			bottomNavigationView,
			findNavController(R.id.nav_host_fragment)
		)
	}
}