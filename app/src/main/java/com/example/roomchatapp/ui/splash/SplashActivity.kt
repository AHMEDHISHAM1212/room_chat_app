package com.example.roomchatapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.roomchatapp.R
import com.example.roomchatapp.ui.home.HomeActivity
import com.example.roomchatapp.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initSplashScreen()
        subscribeToLiveData()


    }

    private fun subscribeToLiveData() {
        viewModel.events.observe(this, ::handelEvents)
    }

    private fun handelEvents(splashViewEvent: SplashViewEvent?) {
        when (splashViewEvent) {
            SplashViewEvent.NavigateToHome -> navigateToHome()
            SplashViewEvent.NavigateToLogin -> navigateToLogin()

            else -> {}
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun initSplashScreen() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                viewModel.redirect()
            }, 20)
    }

}