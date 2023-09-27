package com.example.roomchatapp.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomchatapp.ui.SessionProvider
import com.example.roomchatapp.ui.firestoreDB.UsersDao
import com.example.roomchatapp.ui.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel : ViewModel() {
    val events = MutableLiveData<SplashViewEvent>()

    fun redirect() {

        if (Firebase.auth.currentUser == null) {
            // navigate to the login
            events.postValue(SplashViewEvent.NavigateToLogin)
            return
        }

        UsersDao
            .getUser(Firebase.auth.currentUser?.uid ?: "") { task ->

                if (!task.isSuccessful) {
                    // navigate to login & return
                    events.postValue(SplashViewEvent.NavigateToLogin)
                }
                val user = task.result.toObject(User::class.java)
                SessionProvider.user = user
                // navigate to home
                events.postValue(SplashViewEvent.NavigateToHome)

            }

    }
}