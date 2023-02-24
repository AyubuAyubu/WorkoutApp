package com.example.minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.example.minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //val flStartButton:FrameLayout  = findViewById(R.id.flStart)
        binding?.flStart?.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Here will start the exercise",
                Toast.LENGTH_SHORT
            ).show()
            val intent= Intent(this,ExerciseActivity::class.java)
             startActivity(intent)
        }
}

    override fun onDestroy(){
        super.onDestroy()
        binding=null
    }

}