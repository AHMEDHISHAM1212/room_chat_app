package com.example.roomchatapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityRegisterBinding
import com.example.roomchatapp.ui.login.LoginActivity
import com.example.roomchatapp.ui.showDialog

class RegisterActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() {

        viewModel.errorLiveData.observe(this) { message ->
            showDialog(message = message.message ?: "Some thing went wrong ",
                posActionName = "OK",
                posAction = { dialog, i ->
                    dialog.dismiss()
                }

            )
        }

    }

    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_register)
        //view model init
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        //to connect xml with viewModel
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

        viewBinding.contentRegister
            .tvCreateAnAccount
            .setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }

    }
}