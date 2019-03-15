package com.example.moviesapp.app

import com.example.moviesapp.R
import com.example.moviesapp.BR
import com.example.moviesapp.view.BaseActivity

class MainActivity : BaseActivity<com.example.moviesapp.databinding.ActivityMainBinding, MainActivityViewModel> (){

    override fun getViewModelResId(): Int = BR.mainActivityVM

    override fun getViewModelClass(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_main



}