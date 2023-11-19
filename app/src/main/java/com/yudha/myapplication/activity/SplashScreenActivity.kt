package com.yudha.myapplication.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yudha.myapplication.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actionClick()
    }

    private fun actionClick(){
        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, HomePageActivityPresenter::class.java))
            finish()
        }
    }

}