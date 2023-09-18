package com.example.roomchatapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.ViewError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<ViewError>()
    val isLoading = MutableLiveData<Boolean>()
    val name = MutableLiveData<String>("Ahmed Hisham")
    val email = MutableLiveData<String>("a.hisham.desoky@gmail.com")
    val password = MutableLiveData<String>()
    val passwordConfirmation = MutableLiveData<String>()

    val nameError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val passwordConfirmError = MutableLiveData<String>()

    //    val auth = FirebaseAuth.getInstance()
    private val auth = Firebase.auth

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

        //show loading pb
        isLoading.postValue(true)
        auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isLoading.postValue(false)
                    // show message
                    errorLiveData.postValue(
                        ViewError(
                            message = task.result.user?.uid
                        )
                    )
                } else {
                    // show error message
                    isLoading.postValue(false)
                    errorLiveData.postValue(
                        ViewError(
                            message = task.exception?.localizedMessage
                        )
                    )
                }

            }

    }
}