package com.example.moviesapp.app

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.fragment_custom_progress.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomProgressFragment: AbstractProgressFragment(R.layout.fragment_custom_progress) {

	internal companion object {
		private const val PROGRESS_MAX = 10
	}

	private var title: TextView? = null
	private var progressBar: ProgressBar? = null
	private var noModuleTv: TextView? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		with(view) {
			title = findViewById(R.id.screen_title_tv)
			progressBar = progress_bar_id
			noModuleTv = no_module_tv
		}
		noModuleTv?.visibility = View.GONE
		title?.visibility = View.VISIBLE
		progressBar?.visibility = View.VISIBLE
	}

	override fun onCancelled() {
		noModuleTv?.visibility = View.VISIBLE
		title?.visibility = View.GONE
		progressBar?.visibility = View.GONE
	}

	override fun onFailed(errorCode: Int) {
		GlobalScope.launch{
			delay(5000)
			withContext(Dispatchers.Main) {
				noModuleTv?.visibility = View.VISIBLE
				title?.visibility = View.GONE
				progressBar?.visibility = View.GONE
			}
		}
	}

	override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
		progressBar?.run {
			visibility = View.VISIBLE
			while(bytesDownloaded < bytesTotal) {
				if (bytesTotal == 0L) {
					isIndeterminate = true
				} else {
					progress = (PROGRESS_MAX * bytesDownloaded / bytesTotal).toInt()
					isIndeterminate = false
				}
			}
		}
	}
}