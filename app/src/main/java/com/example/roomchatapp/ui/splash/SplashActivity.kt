package com.example.roomchatapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.roomchatapp.R
import com.example.roomchatapp.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToLogin()
    }

    private fun navigateToLogin() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(
                    Intent(
                        this, LoginActivity::class.java
                    )
                )
                finish()
            }, 2000)

    }
}