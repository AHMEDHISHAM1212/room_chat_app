package com.example.roomchatapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.ViewError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<ViewError>()
    val isLoading = MutableLiveData<Boolean>()
    
    val email = MutableLiveData<String>("a.hisham.desoky@gmail.com")
    val password = MutableLiveData<String>("")

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    val auth = Firebase.auth


    private fun validForm(): Boolean {
        var isValid = true
        if (email.value.isNullOrBlank()) {
            emailError.postValue("Enter Email")
            isValid = false
        } else {
            emailError.postValue("")
        }

        if (password.value.isNullOrBlank()) {
            passwordError.postValue("Enter password")
            isValid = false
        } else {
            passwordError.postValue("")
        }
        return isValid

    }

    fun login() {
        if (!validForm()) return

        isLoading.postValue(true)
        auth.signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // show message is i
                    isLoading.postValue(false)
                    errorLiveData.postValue(
                        ViewError(
                            message = task.result.user?.uid
                        )
                    )
                } else {
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