package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minutesworkout.databinding.ActivityHistoryBinding


class HistoryActivity : AppCompatActivity() {

	private var binding: ActivityHistoryBinding? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityHistoryBinding.inflate(layoutInflater)
		setContentView(binding?.root)
		setSupportActionBar(binding?.toolbarHistoryActivity)

		val actionbar = supportActionBar//actionbar
		if (actionbar != null) {
			actionbar.setDisplayHomeAsUpEnabled(true) //set back button
			actionbar.title = "HISTORY" // Setting a title in the action bar.
		}
		binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
			onBackPressed()
		}

	}
	override fun onDestroy() {
		super.onDestroy()
		binding = null
	}
}


