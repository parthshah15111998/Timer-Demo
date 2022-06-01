package com.example.timerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.timerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownTimer:CountDownTimer?=null
    private var timerDuration:Long=60000
    private var pauseOffset:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTimer.text="${(timerDuration/1000).toString()}"

        binding.btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }
        binding.btnPause.setOnClickListener {
            pauseTimer()
        }
        binding.btnStop.setOnClickListener {
            stopTimer()
        }


    }

    private fun stopTimer() {
        if (countdownTimer != null){
            countdownTimer!!.cancel()
            binding.tvTimer.text="${(timerDuration/1000).toString()}"
            countdownTimer = null
            pauseOffset=0
        }
    }

    private fun pauseTimer() {
        if (countdownTimer != null){
            countdownTimer!!.cancel()
        }
    }

    private fun startTimer(pauseOffsetL: Long) {

        countdownTimer=object : CountDownTimer(timerDuration-pauseOffset,1000) {
            override fun onTick(p0: Long) {
                 pauseOffset= timerDuration-p0
                binding.tvTimer.text=(p0/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"This is Finish",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}