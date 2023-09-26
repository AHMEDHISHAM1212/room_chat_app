package com.example.roomchatapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.roomchatapp.R
import com.example.roomchatapp.databinding.ActivityLoginBinding
import com.example.roomchatapp.ui.home.HomeActivity
import com.example.roomchatapp.ui.register.RegisterActivity
import com.example.roomchatapp.ui.showDialog

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.messageLiveData.observe(this) { message ->
            showDialog(
                message = message.message ?: "Something went wrong!",
                posActionName = "Ok",
                posAction = message.posActionClick,
                negAction = message.negActionClick,
                negActionName = message.negActionName,
                isCancelable = message.isCancelable
            )

        }

        viewModel.events.observe(this, ::handelEvents)
    }

    private fun handelEvents(events: LoginViewEvents?) {
        when (events) {
            LoginViewEvents.NavigateToHome ->
                navigateToHome()

            LoginViewEvents.NavigateToRegister ->
                navigateToRegister()

            else -> {

            }
        }

    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun initViews() {
        viewBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_login)

//        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

    }
}