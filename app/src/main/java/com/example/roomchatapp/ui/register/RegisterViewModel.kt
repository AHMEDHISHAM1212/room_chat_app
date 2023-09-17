package com.example.roomchatapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val name = MutableLiveData<String>("Ahmed Hisham")
    val email = MutableLiveData<String>("a.hisham.desoky@gmail.com")
    val password = MutableLiveData<String>()
    val passwordConfirmation = MutableLiveData<String>()

    val nameError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val passwordConfirmError = MutableLiveData<String>()

    private fun validForm(): Boolean {
        var isValid = true

        if (name.value.isNullOrBlank()) {
            nameError.postValue("Enter your full name!")
            isValid = false
        } else {
            nameError.postValue("")
        }

        if (email.value.isNullOrBlank()) {
            emailError.postValue("Enter your email!")
            isValid = false
        } else {
            emailError.postValue("")
        }

        if (password.value.isNullOrBlank()) {
            passwordError.postValue("Enter password!")
            isValid = false
        } else {
            passwordError.postValue("")
        }

        if (passwordConfirmation.value != password.value) {
            passwordConfirmError.postValue("Password doesn't match")
            isValid = false
        } else {
            passwordConfirmError.postValue("")
        }

        return isValid

    }

    fun register() {
        if (!validForm()) return


    }
}