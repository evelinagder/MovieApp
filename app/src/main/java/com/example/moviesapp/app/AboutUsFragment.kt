package com.example.moviesapp.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviesapp.R

class AboutUsFragment : Fragment(){

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		(activity as AppCompatActivity).supportActionBar?.title = getString(R.string.about_us_screen_title)
		return inflater.inflate(R.layout.fragment_about_us, container, false)
	}
}