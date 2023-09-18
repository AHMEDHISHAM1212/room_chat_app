package com.example.roomchatapp.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityLoginBinding
import com.example.roomchatapp.ui.showDialog

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.errorLiveData.observe(this) { message ->
            showDialog(
                message = message.message ?: "Something went wrong!",
                posActionName = "OK",
                posAction = { dialog, i ->
                    dialog.dismiss()
                }
            )

        }
    }

    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

    }
}