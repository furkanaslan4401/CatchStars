package com.example.catchstar
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.catchstar.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var counter = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    val handler = Handler(Looper.getMainLooper())
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)
        imageArray.add(binding.imageView17)

        for (image in imageArray){
            image.visibility = View.INVISIBLE
        }


    }

    fun Start(view: View){
        binding.myButton.visibility = View.INVISIBLE
        runnable = Runnable {
            for (image in imageArray){
                image.visibility = View.INVISIBLE
            }
            val random = java.util.Random()
            val index = random.nextInt(9)
            imageArray[index].visibility = View.VISIBLE

            handler.postDelayed(runnable,500)
        }
        handler.post(runnable)
        object : CountDownTimer(15500, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.Time.setText("Time: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                binding.Time.text = "Time :0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("oyun bitti")
                alert.setMessage("tekrar oynamak istermisin")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    val intent = getIntent() //burası activity i restart eder
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity,"iyi günler",Toast.LENGTH_LONG).show()
                    finish()
                })
                alert.show()


            }
        }.start()

    }

    fun Star(view: View){
        counter++
        binding.Score.text= "Score: ${counter}"
    }



}


