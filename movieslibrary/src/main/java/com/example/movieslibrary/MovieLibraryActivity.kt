package com.example.movieslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.view.BaseActivity

class MovieLibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_movie_library)
    }
}