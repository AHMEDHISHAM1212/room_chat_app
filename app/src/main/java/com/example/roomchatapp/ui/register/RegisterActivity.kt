package com.example.roomchatapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityRegisterBinding
import com.example.roomchatapp.ui.home.HomeActivity
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

        viewModel.messageLiveData.observe(this) { message ->
            showDialog(
                message = message.message ?: "Some thing went wrong ",
                posActionName = "Ok",
                posAction = message.posActionClick,
                negActionName = message.negActionName,
                negAction = message.negActionClick,
                isCancelable = message.isCancelable
            )
        }

        viewModel.events.observe(this, ::handelEvents)

    }

    private fun handelEvents(events: RegisterViewEvents?) {
        when (events) {
            RegisterViewEvents.NavigateToHome ->
                navigateToHome()

            RegisterViewEvents.NavigateToLogin ->
                navigateToLogin()

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

    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_register)
        //view model init
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        //to connect xml with viewModel
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel


    }
}