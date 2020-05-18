package com.example.moviesapp.app

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.R
import com.example.moviesapp.app.MainActivityViewModel.Companion.NAVIGATION_STEP_HOME
import com.example.moviesapp.app.login.IS_USER_LOGGED
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var navController: NavController
	private lateinit var sharedPreferences: SharedPreferences

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

		navController = findNavController(R.id.nav_host_fragment)

		//Set up BottomNavigationView and NavDrawer
		setUpNavigation()
		connectNavViewWithNavController()

		//listen for registration done and show bottom navigation
		val viewModel: MainActivityViewModel by viewModels()

		viewModel.homeNavigationLiveData.observe(this, Observer {
			if (it == NAVIGATION_STEP_HOME) {

				bottom_nav.visibility = View.VISIBLE
				//This way we don`t go to registration start destination when we select Home tab
				changeStartDestination(R.id.homeFragment)
				supportActionBar?.show()
				drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
			}
		})

		//check if the user is registered and if not change the destination dynamically
		if (!sharedPreferences.getBoolean(IS_USER_LOGGED, false)) {
			goToRegisterAndHideNavigation()
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		return navigateUp(navController, drawer_layout)
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	private fun connectNavViewWithNavController() {

		val drawerLayout = drawer_layout

		val appBarConfiguration =
			AppBarConfiguration.Builder(navController.graph).setDrawerLayout(drawerLayout).build()
		setupActionBarWithNavController(this, navController, appBarConfiguration)
		nav_view.setupWithNavController(navController)

		nav_view.menu.findItem(R.id.logout).setOnMenuItemClickListener { item ->
			sharedPreferences.edit().putBoolean(IS_USER_LOGGED, false).apply()
			goToRegisterAndHideNavigation()
			true
		}
	}

	private fun setUpNavigation() {
		val bottomNavigationView = findViewById<View>(R.id.bottom_nav) as BottomNavigationView
		bottomNavigationView.setupWithNavController(navController)

		/*bottom_nav.menu.findItem(R.id.nav_graph_live_tv).setOnMenuItemClickListener{
			val installMonitor = DynamicInstallMonitor()
			navController.navigate(
				it.itemId,
				null,
				null,
				DynamicExtras(installMonitor)
			)
			if(installMonitor.isInstallRequired){
				installMonitor.status.observe(this, object : Observer<SplitInstallSessionState> {
					override fun onChanged(sessionState: SplitInstallSessionState) {
						when (sessionState.status()) {
							SplitInstallSessionStatus.INSTALLED -> {
								// Call navigate again here or after user taps again in the UI:
								// navController.navigate(destinationId, destinationArgs, null, null)
							}

							// Handle all remaining states:
							SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {}
							SplitInstallSessionStatus.FAILED -> {}
							SplitInstallSessionStatus.CANCELED -> {}
						}

						if (sessionState.hasTerminalStatus()) {
							installMonitor.status.removeObserver(this);
						}
					}
				})
			}

			true
		}*/
	}

	/**
	 * This changes the start Destination dynamically
	 */
	private fun changeStartDestination(@IdRes startDestination: Int) {
		val graph = navController.graph
		graph.startDestination = startDestination
		navController.graph = graph
	}

	private fun goToRegisterAndHideNavigation() {
		supportActionBar?.hide()
		drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
		changeStartDestination(R.id.navigation_reg)
		bottom_nav.visibility = View.GONE
	}
}