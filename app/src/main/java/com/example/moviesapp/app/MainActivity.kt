package com.example.moviesapp.app

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.moviesapp.R
import com.example.moviesapp.BR
import com.example.moviesapp.view.BaseActivity

class MainActivity : BaseActivity<com.example.moviesapp.databinding.ActivityMainBinding, MainActivityViewModel> (){

    override fun getViewModelResId(): Int = BR.mainActivityVM

    override fun getViewModelClass(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}