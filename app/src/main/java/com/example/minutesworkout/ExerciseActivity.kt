package com.example.minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minutesworkout.databinding.ActivityExerciseBinding
import com.example.minutesworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

	private var binding :ActivityExerciseBinding? = null

    private var restTimer:CountDownTimer?=null
	private var restTimerDuration:Long=1
	private var restProgress=0

	private var exerciseTimer:CountDownTimer?=null
	private var exerciseTimerDuration:Long=1
	private var exerciseProgress=0
	private var  exerciseList: ArrayList<ExerciseModel>? = null

	private var currentExercisePosition= -1

	private var tts : TextToSpeech? = null

	private var player : MediaPlayer? = null



	private var exerciseAdapter:ExerciseStatusAdapter?=null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding= ActivityExerciseBinding.inflate(layoutInflater)
		setContentView(binding?.root)
		setSupportActionBar(binding?.toolbarExercise)

		//setting back button
		if(supportActionBar != null){
			supportActionBar?.setDisplayHomeAsUpEnabled(true)
		}
		//Access our Object
		exerciseList=Constants.defaultExerciseList()
        tts= TextToSpeech(this,this)

		binding?.toolbarExercise?.setNavigationOnClickListener {
			customDialogForBackButton()
		}

		setupRestView()
		setupExerciseStatusRecyclerView()
	}

	private fun setupExerciseStatusRecyclerView(){
		binding?.rvExerciseStatus?.layoutManager =
			LinearLayoutManager(this,
				LinearLayoutManager.HORIZONTAL,false)
		exerciseAdapter	= ExerciseStatusAdapter(exerciseList!!)
		binding?.rvExerciseStatus?.adapter=exerciseAdapter
	}

	private fun setupRestView(){

		try {
			val soundURI = Uri.parse(
				"android.resource://com.example.minutesworkout/"
						+ R.raw.press_start
			)
			player = MediaPlayer.create(applicationContext, soundURI)
			player?.isLooping = false
			player?.start()
		}catch (e: Exception){
				e.printStackTrace()
			}
		binding?.flProgressBar?.visibility=View.VISIBLE
		binding?.tvTitle?.visibility=View.VISIBLE
		binding?.tvExercise?.visibility=View.INVISIBLE
		binding?.flProgressBarExercise?.visibility=View.INVISIBLE
		binding?.ivImage?.visibility=View.INVISIBLE

		if(restTimer != null){
			restTimer?.cancel()
			restProgress=0
		}
		setRestProgressBar()
	}

	private fun setupExerciseView(){
		binding?.flProgressBar?.visibility=View.INVISIBLE
		binding?.tvTitle?.visibility=View.INVISIBLE
		binding?.tvExercise?.visibility=View.VISIBLE
		binding?.flProgressBarExercise?.visibility=View.VISIBLE
		binding?.ivImage?.visibility=View.VISIBLE

		if(exerciseTimer != null){
			exerciseTimer?.cancel()
			exerciseProgress=0
		}

		speakOut(exerciseList!![currentExercisePosition].getName())

		binding?.ivImage?.
				setImageResource(exerciseList!![currentExercisePosition].getImage())
		binding?.tvExercise?.
			   text=exerciseList!![currentExercisePosition].getName()

		setExerciseProgressBar()
	}

	private fun setRestProgressBar(){
		binding?.progressBar?.progress=restProgress

		restTimer=object :CountDownTimer(restTimerDuration*1000,1000){
			override fun onTick(millisUntilFinished: Long) {
				//increase 1 sec every tick
				restProgress++
				//change the progress bar by decrease 10-restProgress
				binding?.progressBar?.progress=10-restProgress
				binding?.tvTimer?.text=(10-restProgress).toString()
			}

			override fun onFinish() {
				currentExercisePosition++

				exerciseList!![currentExercisePosition].setIsSelected(true)
				exerciseAdapter!!.notifyDataSetChanged()
				setupExerciseView()
				//restTimer?.cancel()
			}

		}.start()
	}
	private fun setExerciseProgressBar(){
		//restTimer?.start()
		binding?.progressBarExercise?.progress=exerciseProgress
		exerciseTimer=object :CountDownTimer(exerciseTimerDuration*1000,1000){
			override fun onTick(millisUntilFinished: Long) {
				//increase 1 sec every tick
				exerciseProgress++
				//change the progress bar by decrease 10-restProgress
				binding?.progressBarExercise?.progress=30 - exerciseProgress
				binding?.tvExerciseTimer?.text = (30 - exerciseProgress).toString()
			}

			override fun onFinish() {
				if(currentExercisePosition < exerciseList?.size!! - 1){
					exerciseList!![currentExercisePosition].setIsSelected(false)
					exerciseList!![currentExercisePosition].setIsCompleted(true)
					exerciseAdapter!!.notifyDataSetChanged()
					setupRestView()
				}else{
					finish()
					val intent= Intent(this@ExerciseActivity,FinishActivity::class.java)
					startActivity(intent)
				}
				//restTimer?.cancel()
			}

		}.start()
	}
	override fun onDestroy() {
		super.onDestroy()
		if(restTimer != null){
			restTimer?.cancel()
			restProgress=0
		}
		if(exerciseTimer != null){
			exerciseTimer?.cancel()
			exerciseProgress=0
		}
       //shutting down text speech
		if(tts != null){
			tts!!.stop()
			tts!!.shutdown()
		}
		binding=null
	}

	override fun onInit(status: Int) {
		if(status == TextToSpeech.SUCCESS){
			var result=tts?.setLanguage(Locale.US)

			if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
				Log.e("TTS","Language not supported")
			}else{
				Log.e("TTS","Initialized Failed")
			}
		}
	}

	private fun speakOut(text:String){
		tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
	}

	override fun onBackPressed() {
		customDialogForBackButton()
		super.onBackPressed()
	}
	private fun customDialogForBackButton(){
		val customDialog= Dialog(this)
		val dialogBinding=DialogCustomBackConfirmationBinding.inflate(layoutInflater)

		customDialog.setContentView(dialogBinding.root)
		customDialog.setCanceledOnTouchOutside(false)

		dialogBinding.btnYes.setOnClickListener {
                  this@ExerciseActivity.finish()
					customDialog.dismiss()
		}

		dialogBinding.btnNo.setOnClickListener {
					customDialog.dismiss()
		}
		customDialog.show()
   }
}