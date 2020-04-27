package com.example.newsfeed

import android.os.Bundle
import com.example.moviesapp.view.BaseActivity

class NewsFeedActivity :
    BaseActivity<com.example.newsfeed.databinding.ActivityNewsFeedBinding, NewsFeedActivityViewModel>() {

    override fun getViewModelResId(): Int = BR.newsFeedActivityVM

    override fun getViewModelClass(): Class<NewsFeedActivityViewModel> =
        NewsFeedActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_news_feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}