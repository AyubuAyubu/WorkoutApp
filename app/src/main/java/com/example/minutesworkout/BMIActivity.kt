package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minutesworkout.databinding.ActivityBmiBinding

class BMIActivity : AppCompatActivity() {
	private var binding: ActivityBmiBinding? = null
	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		binding = ActivityBmiBinding.inflate(layoutInflater)
		setContentView(binding?.root)


		setSupportActionBar(binding?.toolbarBmiActivity)

		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.title = "CALCULATE BMI"
		binding?.toolbarBmiActivity?.setNavigationOnClickListener {
			onBackPressed()
		}

	}
}