package com.example.roomchatapp.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

    }

    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_register)
        //view model init
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

    }
}