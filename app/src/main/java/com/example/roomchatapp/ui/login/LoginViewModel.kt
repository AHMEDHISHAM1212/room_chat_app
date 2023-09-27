package com.example.roomchatapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.Message
import com.example.roomchatapp.ui.SessionProvider
import com.example.roomchatapp.ui.SingleLiveEvent
import com.example.roomchatapp.ui.firestoreDB.UsersDao
import com.example.roomchatapp.ui.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    val messageLiveData = SingleLiveEvent<Message>()
    val isLoading = MutableLiveData<Boolean>()

    val email = MutableLiveData<String>("a.hisham.desoky@gmail.com")
    val password = MutableLiveData<String>("")

    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()

    val events = SingleLiveEvent<LoginViewEvents>()

    private val auth = Firebase.auth


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
                    getUserFromFireStore(task.result.user?.uid)
                } else {
                    isLoading.postValue(false)
                    messageLiveData.postValue(
                        Message(
                            message = task.exception?.localizedMessage
                        )
                    )
                }
            }
    }

    private fun getUserFromFireStore(uid: String?) {
        UsersDao
            .getUser(uid) { task ->
                isLoading.value = false
                if (task.isSuccessful) {
                    val user = task.result.toObject(User::class.java)
                    // save
                    SessionProvider.user = user
                    //show dialog and navigate
                    messageLiveData.postValue(
                        Message(
                            message = "Logged in successfully",
                            posActionName = "Ok",
                            posActionClick = {
                                //navigate to home
                                events.postValue(LoginViewEvents.NavigateToHome)
                            },
                            isCancelable = false
                        )
                    )
                }
            }

    }

    fun navigateToRegister() {
        events.postValue(LoginViewEvents.NavigateToRegister)
    }
}