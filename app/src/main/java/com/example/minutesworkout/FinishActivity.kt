package com.example.minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.minutesworkout.databinding.ActivityExerciseBinding
import com.example.minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
	private var binding : ActivityFinishBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityFinishBinding.inflate(layoutInflater)
		setContentView(binding?.root)
		setSupportActionBar(binding?.toolbarFinishActivity)

		//setting back button
		if (supportActionBar != null) {
			supportActionBar?.setDisplayHomeAsUpEnabled(true)
		}

		binding?.toolbarFinishActivity?.setNavigationOnClickListener {
			onBackPressed()
		}
		binding?.btnFinish?.setOnClickListener {
			finish()
		}
		val dao = (application as WorkoutApp).db.historyDao()
		addDateToDatabase(dao)
	}
		fun addDateToDatabase(historyDao: HistoryDao) {
			val c = Calendar.getInstance() // Calendars Current Instance
			val dateTime = c.time // Current Date and Time of the system.
			Log.e("Date : ", "" + dateTime) // Printed in the log.


			val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()) // Date Formatter
			val date = sdf.format(dateTime) // dateTime is formatted in the given format.
			Log.e("Formatted Date : ", "" + date) // Formatted date is printed in the log.

			lifecycleScope.launch {
				historyDao.insert(HistoryEntity(date)) // Add date function is called.
				Log.e(
					"Date : ",
					"Added..."
				) // Printed in log which is printed if the complete execution is done.
			}
		}
		//END
	}

