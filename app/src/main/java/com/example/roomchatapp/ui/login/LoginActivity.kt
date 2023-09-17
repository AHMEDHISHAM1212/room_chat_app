package com.example.roomchatapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_login)
    }
}