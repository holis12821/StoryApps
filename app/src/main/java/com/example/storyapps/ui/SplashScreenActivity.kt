package com.example.storyapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storyapps.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initView()
    }

    private fun initView() {
        showSplashScreen()
    }

    private fun showSplashScreen() {
        object : Thread() {
            override fun run() {
                try {
                    sleep(3_000)
                    val intent = Intent(
                        this@SplashScreenActivity,
                        MainActivity::class.java
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}