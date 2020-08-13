package com.example.moviesapp.utils

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Set view visible or gone based on provided boolean flag
 */
@BindingAdapter("visibleOrNot")
fun View.setVisible(show: Boolean) {
	visibility = if (show) View.VISIBLE else View.INVISIBLE
}